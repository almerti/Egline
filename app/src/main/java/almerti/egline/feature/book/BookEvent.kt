package almerti.egline.feature.book


sealed class BookEvent {
    data class AddComment(val comment : String) : BookEvent()
    data class DeleteComment(val comment : String) : BookEvent()
    data class UpdateComment(val comment : String) : BookEvent()
    data class AddRate(val rate : Int) : BookEvent()
    data class UpdateRate(val rate : Int) : BookEvent()
    data class RateComment(val commentId : Int, val rate : Int) : BookEvent()
    data class DownloadChapter(val chapterId : Int) : BookEvent()
    data class DownloadAllChapters(val bookId : Int) : BookEvent()
}
