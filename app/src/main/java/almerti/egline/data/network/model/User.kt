package almerti.egline.data.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class User(
    val id : Int ,
    @SerializedName("display_name")
    val displayName : String ,
    val email : String ,
    val password : String ,
    val avatar : ByteArray ,
    @SerializedName(value = "saved_books" , alternate = ["books"])
    val savedBooks : JsonObject ,
)
