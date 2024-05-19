package almerti.egline.feature.profile.edit

import almerti.egline.data.model.User
import almerti.egline.data.repository.UserRepository
import almerti.egline.data.use_case.ValidateDisplayName
import almerti.egline.data.use_case.ValidateEmail
import almerti.egline.data.use_case.ValidatePassword
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
    private val editProfileValidationEventChannel = Channel<EditProfileValidationEvent>()
    val editProfileValidationEvents = editProfileValidationEventChannel.receiveAsFlow()

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
        val emailValidation = validateEmail.execute(state.email)
        val displayNameValidation = validateDisplayName.execute(state.displayName)
        val passwordValidation = validatePassword.execute(state.password, false)
    }

    sealed class EditProfileValidationEvent {
        data object Success : EditProfileValidationEvent()
        data object Failed : EditProfileValidationEvent()
    }
}
