package almerti.egline.data.network.model

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class User (
    val id: Int,
    @SerializedName("display_name")
    val displayName: String,
    val email: String,
    val password: String,
    val avatar: ByteArray,
    val saved_books: JsonArray,
)