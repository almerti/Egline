package almerti.egline.feature.profile.edit

data class EditProfileState(
    val avatar: ByteArray = byteArrayOf(),
    val email: String = "",
    val emailError: String? = null,
    val displayName: String = "",
    val displayNameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val newPassword: String = "",
    val newPasswordError: String? = null
)
