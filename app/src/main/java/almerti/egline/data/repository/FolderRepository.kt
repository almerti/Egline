package almerti.egline.data.repository

import almerti.egline.data.model.Folder

interface FolderRepository {
    suspend fun addBooks(folder : Folder)
    suspend fun removeBook(folder : Folder)
    suspend fun removeFolder(folder : Folder)
    suspend fun getAllFolders() : List<Folder>
    suspend fun getFolderByName(folderName : String) : Folder?

}
