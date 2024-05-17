package almerti.egline.feature.register

sealed class RegisterFormEvent {
    data class EmailChanged(val email: String) : RegisterFormEvent()
    data class PasswordChanged(val password: String) : RegisterFormEvent()
    data class DisplayNameChanged(val displayName: String) : RegisterFormEvent()
    data object Submit : RegisterFormEvent()
}
