package almerti.egline.data.implementation

import almerti.egline.data.model.Folder
import almerti.egline.data.repository.FolderRepository
import almerti.egline.data.repository.UserRepository
import almerti.egline.data.source.database.EglineDatabase
import almerti.egline.data.source.database.model.SavedBook
import com.google.gson.JsonObject
import dagger.Lazy
import javax.inject.Inject

class FolderRepositoryimpl @Inject constructor(
    private val eglineDatabase : EglineDatabase,
    private val UserRepository : Lazy<UserRepository>,
) : FolderRepository {
    override suspend fun addBooks(folder : Folder) {
        eglineDatabase.SavedBookDao().upsertSavedBooks(folderToSavedBook(folder))
        UserRepository.get().update()
    }

    override suspend fun removeBook(folder : Folder) {
        eglineDatabase.SavedBookDao().deleteSavedBooks(folderToSavedBook(folder))
        UserRepository.get().update()
    }

    override suspend fun removeFolder(folder : Folder) {
        eglineDatabase.SavedBookDao().deleteFolder(folder.folderName)
        UserRepository.get().update()
    }

    override suspend fun getAll() : List<Folder> {
        return savedBooksToFolder(eglineDatabase.SavedBookDao().getAllSavedBooks())
    }

    override suspend fun saveFoldersJson(jsonObject : JsonObject) {
        val folders = mutableListOf<Folder>()

        for ((key, value) in jsonObject.entrySet()) {
            val folderName = key
            val bookIds = value.asJsonPrimitive.asString.removeSurrounding("[", "]")
                .split(",")
                .map {it.trim().toInt()}
                .toMutableList()

            folders.add(Folder(folderName, bookIds))
        }


        val savedBooks = mutableListOf<SavedBook>()
        folders.forEach {
            savedBooks.addAll(folderToSavedBook(it))
        }
        eglineDatabase.SavedBookDao().upsertSavedBooks(savedBooks)
        UserRepository.get().update()
    }

    override suspend fun getByName(folderName : String) : Folder {
        return savedBooksToFolder(
            eglineDatabase.SavedBookDao().getSavedBooksByFolderName(folderName),
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
