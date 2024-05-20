package almerti.egline.data.model

data class Comment(
    val id : Int = -1,
    val userId : Int,
    val bookId : Int,
    val chapterId : Int? = null,
    val text : String,
    val rating : Int,
)
