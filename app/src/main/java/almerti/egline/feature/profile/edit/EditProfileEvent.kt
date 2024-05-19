package almerti.egline.feature.profile.edit

sealed class EditProfileEvent {
    data class AvatarChanged(val avatar: ByteArray) : EditProfileEvent()
    data class EmailChanged(val email: String) : EditProfileEvent()
    data class DisplayNameChanged(val displayName: String) : EditProfileEvent()
    data class PasswordChanged(val password: String) : EditProfileEvent()
    data class NewPasswordChanged(val newPassword: String) : EditProfileEvent()
    data object Submit : EditProfileEvent()
}
