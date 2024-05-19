package almerti.egline.data.implementation

import almerti.egline.data.model.Folder
import almerti.egline.data.repository.FolderRepository
import almerti.egline.data.repository.UserRepository
import almerti.egline.data.source.database.EglineDatabase
import almerti.egline.data.source.database.model.SavedBook
import com.google.gson.JsonObject
import dagger.Lazy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FolderRepositoryimpl @Inject constructor(
    private val eglineDatabase : EglineDatabase,
    private val userRepositoryLazy : Lazy<UserRepository>,
) : FolderRepository {


    override suspend fun addBooks(folder : Folder) {
        eglineDatabase.SavedBookDao().upsertSavedBooks(folderToSavedBook(folder))
        userRepositoryLazy.get().sendDataToServer()
    }

    override suspend fun addFolder(folderName : String) {
        eglineDatabase.SavedBookDao().upsertSavedBook(SavedBook(-1, folderName))
    }

    override suspend fun removeBooksFromFolder(folder : Folder) {
        eglineDatabase.SavedBookDao().deleteSavedBooks(folderToSavedBook(folder))
        userRepositoryLazy.get().sendDataToServer()
    }

    override suspend fun removeFolder(folderName : String) {
        eglineDatabase.SavedBookDao().deleteFolder(folderName)
        userRepositoryLazy.get().sendDataToServer()
    }

    override suspend fun renameFolder(oldName : String, newName : String) {
        eglineDatabase.SavedBookDao().updateFolderName(oldName, newName)
    }

    override suspend fun getAll() : Flow<List<Folder>> {
        return eglineDatabase.SavedBookDao().getAllSavedBooks().map {
            savedBooksToFolder(it)
        }
    }

    override suspend fun removeAll() {
        eglineDatabase.SavedBookDao().deleteAllSavedBooks()
    }

    override suspend fun saveFoldersJson(jsonObject : JsonObject) {
        try {

            val folders = mutableListOf<Folder>()

            for ((key, value) in jsonObject.entrySet()) {
                val bookIds = value.asJsonPrimitive.asString.removeSurrounding("[", "]")
                    .split(",")
                    .map {it.trim().toIntOrNull() ?: -1}
                    .toMutableList()
                folders.add(Folder(key, bookIds))
            }

            val savedBooks = mutableListOf<SavedBook>()
            folders.forEach {
                savedBooks.addAll(folderToSavedBook(it))
            }
            eglineDatabase.SavedBookDao().upsertSavedBooks(savedBooks)
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getByName(folderName : String) : Folder {
        return savedBooksToFolder(
            eglineDatabase.SavedBookDao().getSavedBooksByFolderName(folderName).first(),
        )[0]
    }

    private fun folderToSavedBook(folder : Folder) : List<SavedBook> {

        val savedBooks = mutableListOf<SavedBook>()
        folder.bookIds.forEach {
            savedBooks.add(
                SavedBook(
                    folderName = folder.folderName,
                    bookId = it,
                ),
            )
        }

        return savedBooks
    }

    private fun savedBookToFolder(savedBook : SavedBook) : Folder {
        return Folder(
            folderName = savedBook.folderName,
            bookIds = mutableListOf(savedBook.bookId),
        )
    }

    private fun savedBooksToFolder(savedBooks : List<SavedBook>) : List<Folder> {
        val folders = mutableMapOf<String, Folder>()

        savedBooks.forEach {book ->
            val folderName = book.folderName
            val bookId = book.bookId

            val folder = folders.getOrPut(folderName) {
                Folder(folderName, mutableListOf())
            }

            folder.bookIds.add(bookId)
        }

        return folders.values.toList()
    }
}
