package almerti.egline.data.model

data class Comment(
    val userId : Int,
    val bookId : Int,
    val chapterId : Int?,
    val text : String,
    val rating : Int,
)
