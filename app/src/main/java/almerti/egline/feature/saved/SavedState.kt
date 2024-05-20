package almerti.egline.feature.saved

import almerti.egline.data.model.Book

data class SavedState(
    val books : List<Book>? = null,
)
