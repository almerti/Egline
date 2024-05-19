package almerti.egline.data.source.network.model

import com.google.gson.annotations.SerializedName

data class UserEdit(
    val avatar: ByteArray = byteArrayOf(),
    val email: String,
    @SerializedName(value = "display_name")
    val displayName: String,
    val password: String,
    @SerializedName(value = "new_password")
    val newPassword: String
)
