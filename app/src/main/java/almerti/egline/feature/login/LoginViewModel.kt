package almerti.egline.feature.login

import almerti.egline.data.repository.UserRepository
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
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword()
) : ViewModel() {
    var state by mutableStateOf(LoginFormState())
    private val loginValidationEventChannel = Channel<LoginValidationEvent>()
    val loginValidationEvents = loginValidationEventChannel.receiveAsFlow()
    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                state = state.copy(email = event.email.lowercase(), emailError = null)
            }

            is LoginFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password, passwordError = null)
            }

            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password, false)

        val isError = listOf(
            emailResult,
            passwordResult,
        ).any {!it.successful}

        if (isError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            val loginResult = userRepository.login(state.email, state.password)
            state = state.copy(
                loginResult = loginResult,
                emailError = null,
                passwordError = if (loginResult != "OK") "Wrong password" else null,
            )

            if (loginResult == "OK") {
                loginValidationEventChannel.send(LoginValidationEvent.Success)
            }
        }
    }

    sealed class LoginValidationEvent {
        data object Success : LoginValidationEvent()
    }
}
