package almerti.egline.feature.catalog

import almerti.egline.data.model.Book
import almerti.egline.feature.favorite.BookItem

data class CatalogState(
    val books : List<Book?>? = null,
    val bookItems : List<BookItem>? = null
)
