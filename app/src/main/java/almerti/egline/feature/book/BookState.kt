package almerti.egline.feature.book

import almerti.egline.data.model.Book
import almerti.egline.data.model.Chapter
import almerti.egline.data.model.Comment

data class BookState(
    var book : Book? = null,
    var comments : List<Comment> = emptyList(),
    var chapters : List<Chapter> = emptyList()
)
