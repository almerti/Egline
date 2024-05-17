package almerti.egline.feature.favorite

import almerti.egline.data.model.Folder

sealed class FavoriteEvent {
    data class AddFolder(val folderName: String) : FavoriteEvent()
    data class ChangeCurrentFolder(val folder: Folder) : FavoriteEvent()
}
