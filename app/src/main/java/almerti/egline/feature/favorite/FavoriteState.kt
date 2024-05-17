package almerti.egline.feature.favorite

import almerti.egline.data.model.Folder

data class FavoriteState(
    val folders: List<Folder> = emptyList(),
    val currentFolder: Folder? = null,
    val bookList: List<BookItem> = emptyList()
)
