package almerti.egline.feature.profile.edit

import almerti.egline.data.model.User
import almerti.egline.data.repository.UserRepository
import almerti.egline.data.source.network.model.UserEdit
import almerti.egline.data.use_case.ValidateDisplayName
import almerti.egline.data.use_case.ValidateEmail
import almerti.egline.data.use_case.ValidatePassword
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validateDisplayName: ValidateDisplayName = ValidateDisplayName(),
    private val validatePassword: ValidatePassword = ValidatePassword()
) : ViewModel() {
    var state by mutableStateOf(EditProfileState())
    var selectedImageUri = mutableStateOf<Uri?>(null)
    private val editProfileValidationEventChannel = Channel<EditProfileValidationEvent>()
    val editProfileValidationEvents = editProfileValidationEventChannel.receiveAsFlow()
    var isEditPressed = mutableStateOf(false)

    private lateinit var userFlow: Flow<User>

    private val _userState = MutableStateFlow<User?>(null)
    val userState: StateFlow<User?> = _userState

    init {
        getUser()
    }

    fun initState() {
        if (state.displayName == "")
            state = state.copy(
                avatar = _userState.value?.avatar!!,
                email = _userState.value?.email!!,
                displayName = _userState.value?.displayName!!,
            )
    }

    private fun getUser() {
        viewModelScope.launch {
            userFlow = userRepository.get()
            userFlow.collect {_userState.value = it}
        }
    }

    fun onEvent(event: EditProfileEvent) {
        when (event) {
            is EditProfileEvent.AvatarChanged -> {
                state = state.copy(avatar = event.avatar)
            }

            is EditProfileEvent.DisplayNameChanged -> {
                state = state.copy(displayName = event.displayName)
            }

            is EditProfileEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is EditProfileEvent.NewPasswordChanged -> {
                state = state.copy(newPassword = event.newPassword)
            }

            is EditProfileEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            EditProfileEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        isEditPressed.value = true
        val emailValidation = validateEmail.execute(state.email)
        val displayNameValidation = validateDisplayName.execute(state.displayName)
        val passwordValidation = validatePassword.execute(state.password, false)
        val newPasswordValidation = validatePassword.execute(state.newPassword, true)

        var isError = listOf(
            emailValidation,
            displayNameValidation,
            passwordValidation,
        ).any {!it.successful}

        if (!isError) {
            if (!newPasswordValidation.successful && state.newPassword != "") {
                isError = true
            }
        }

        if (isError) {
            state = state.copy(
                emailError = emailValidation.errorMessage,
                passwordError = passwordValidation.errorMessage,
                displayNameError = displayNameValidation.errorMessage,
                newPasswordError = if (state.newPassword.isNotEmpty())
                    newPasswordValidation.errorMessage
                else null,
            )
            isEditPressed.value = false
            return
        }

        viewModelScope.launch {
            val editUser = UserEdit(
                email = state.email,
                displayName = state.displayName,
                password = state.password,
                newPassword = state.newPassword,
                avatar = if (selectedImageUri.value == null)
                    byteArrayOf()
                else state.avatar,
            )

            state = state.copy(
                emailError = null,
                displayNameError = null,
                passwordError = null,
                newPasswordError = null,
            )

            val updateResult = userRepository.edit(_userState.value?.id!!, editUser)

            state = state.copy(
                emailError = if (updateResult.contains("email")) "Email is already in use" else null,
                displayNameError = if (updateResult.contains("display")) "Display name is already in use" else null,
                passwordError = if (updateResult.contains("Password")) "Wrong password" else null,
                newPasswordError = null,
            )

            when (updateResult) {
                "OK" -> {
                    editProfileValidationEventChannel.send(EditProfileValidationEvent.Success)
                }

                "No response" -> {
                    editProfileValidationEventChannel.send(EditProfileValidationEvent.Failed)
                    isEditPressed.value = false
                }

                else -> {
                    isEditPressed.value = false
                }
            }
        }
    }

    sealed class EditProfileValidationEvent {
        data object Success : EditProfileValidationEvent()
        data object Failed : EditProfileValidationEvent()
    }
}
