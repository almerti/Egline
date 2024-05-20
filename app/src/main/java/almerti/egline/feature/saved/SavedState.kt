package almerti.egline.feature.saved

import almerti.egline.data.model.Book
import almerti.egline.feature.favorite.BookItem

data class SavedState(
    val books : List<Book>? = null,
    val bookItems : List<BookItem> = emptyList()
)
