package almerti.egline.data.network.model

import com.google.gson.annotations.SerializedName

data class BookRate(
    @SerializedName("book_id")
    val bookId : Int ,
    @SerializedName("user_id")
    val userId : Int ,
    val rate : Int
)
