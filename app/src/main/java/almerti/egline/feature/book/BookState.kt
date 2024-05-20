package almerti.egline.feature.book

import almerti.egline.data.model.Book
import almerti.egline.data.model.Chapter
import almerti.egline.data.model.Comment
import kotlin.reflect.KProperty

data class BookState(
    var book : Book? = null,
    var comments : List<Comment> = emptyList(),
    var chapters : List<Chapter> = emptyList()
) {
    operator fun getValue(nothing : Nothing?, property : KProperty<*>) : Any {
        return when (property.name) {
            "book" -> book
            "comments" -> comments
            "chapters" -> chapters
            else -> throw IllegalArgumentException("Property $property.name is not a part of BookState")
        }!!
    }
}
