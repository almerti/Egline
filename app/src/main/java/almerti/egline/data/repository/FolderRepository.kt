package almerti.egline.data.repository

import almerti.egline.data.model.Folder
import com.google.gson.JsonObject

interface FolderRepository {
    suspend fun addBooks(folder : Folder)
    suspend fun removeBook(folder : Folder)
    suspend fun removeFolder(folder : Folder)
    suspend fun getAllFolders() : List<Folder>
    suspend fun saveFoldersJson(jsonObject : JsonObject)
    suspend fun getFolderByName(folderName : String) : Folder?

}
