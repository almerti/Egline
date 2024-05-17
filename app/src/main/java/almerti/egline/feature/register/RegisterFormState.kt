package almerti.egline.feature.register

data class RegisterFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val displayName: String = "",
    val displayNameError: String? = null,
    val registerResult: String = "",
)
