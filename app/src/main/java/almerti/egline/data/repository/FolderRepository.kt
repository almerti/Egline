package almerti.egline.data.repository

import almerti.egline.data.model.Folder
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow

interface FolderRepository {
    suspend fun addBooks(folder : Folder)
    suspend fun addFolder(folderName : String)
    suspend fun removeBooksFromFolder(folder : Folder)
    suspend fun removeFolder(folderName : String)
    suspend fun renameFolder(oldName : String, newName : String)
    suspend fun getAll() : Flow<List<Folder>>
    suspend fun removeAll()
    suspend fun saveFoldersJson(jsonObject : JsonObject)
    suspend fun getByName(folderName : String) : Folder?

}
