package almerti.egline.feature.favorite

import almerti.egline.data.model.Folder

sealed class FavoriteEvent {
    data class ChangeCurrentFolder(val folder : Folder) : FavoriteEvent()
    data class RemoveBookFromFolder(val folder : Folder, val bookId : String) : FavoriteEvent()
    data class ChangeBookInFolder(val folder : Folder, val bookId : String) : FavoriteEvent()
    data class FindBookInFolder(val folder : Folder, val bookName : String) : FavoriteEvent()
    data class AddFolder(val folderName : String) : FavoriteEvent()
    data class RemoveFolder(val folderName : String) : FavoriteEvent()
    data class ChangeFolderName(val oldFolderName : String, val newFolderName : String) :
        FavoriteEvent()

}
