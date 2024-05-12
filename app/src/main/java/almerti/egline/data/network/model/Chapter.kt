package almerti.egline.data.network.model

import com.google.gson.annotations.SerializedName


data class Chapter(
    val id: Int ,
    @SerializedName( "book_id")
    val bookId: Int ,
    val title: String ,
    val number : Int ,
    val filepath:String,
    val date: Long
)
