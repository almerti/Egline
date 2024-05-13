package almerti.egline.data.network.model

import com.google.gson.annotations.SerializedName

data class Comment(
    val id: Int,
    @SerializedName(value = "book_id")
    val bookId: Int,
    @SerializedName(value = "user_id")
    val userId: Int,
    @SerializedName(value = "chapter_id")
    val chapterId: Int,
    val text: String,
    val upvotes: Int,
    val downvotes: Int
)
