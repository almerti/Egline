package almerti.egline.feature.register

import almerti.egline.data.repository.UserRepository
import almerti.egline.data.model.User
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
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validateDisplayName: ValidateDisplayName = ValidateDisplayName(),
    private val validatePassword: ValidatePassword = ValidatePassword()
) : ViewModel() {
    var state by mutableStateOf(RegisterFormState())
    private val registerValidationEventChannel = Channel<RegisterValidationEvent>()
    val registerValidationEvents = registerValidationEventChannel.receiveAsFlow()

    fun onEvent(event: RegisterFormEvent) {
        when (event) {
            is RegisterFormEvent.EmailChanged -> {
                state = state.copy(email = event.email.lowercase(), emailError = null)
            }

            is RegisterFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password, passwordError = null)
            }

            is RegisterFormEvent.DisplayNameChanged -> {
                state = state.copy(displayName = event.displayName, displayNameError = null)
            }

            is RegisterFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password, true)
        val displayNameResult = validateDisplayName.execute(state.displayName)

        val isError = listOf(
            emailResult,
            passwordResult,
            displayNameResult,
        ).any {!it.successful}

        if (isError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                displayNameError = displayNameResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            val user = User(
                id = -1,
                email = state.email,
                displayName = state.displayName,
                password = state.password,
                avatar = ByteArray(0),
            )

            val registerResult = userRepository.register(user)
            state = state.copy(
                registerResult = registerResult,
                emailError = if (registerResult.contains("email")) "Email is already in use" else null,
                displayNameError = if (registerResult.contains("display")) "Display name is already in use" else null,
                passwordError = null,
            )

            if (registerResult == "OK") {
                registerValidationEventChannel.send(RegisterValidationEvent.Success)
            }
        }
    }

    sealed class RegisterValidationEvent {
        data object Success : RegisterValidationEvent()
    }
}
