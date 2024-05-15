package almerti.egline.data.repository

import almerti.egline.data.model.Folder
import almerti.egline.data.source.database.EglineDatabase
import almerti.egline.data.source.database.model.SavedBook
import dagger.Lazy
import javax.inject.Inject

class FolderRepositoryimpl @Inject constructor(
    private val eglineDatabase : EglineDatabase,
    private val UserRepository : Lazy<UserRepository>,
) : FolderRepository {
    override suspend fun addBooks(folder : Folder) {
        eglineDatabase.SavedBookDao().upsertSavedBooks(folderToSavedBook(folder))
        UserRepository.get().updateCurrent()
    }

    override suspend fun removeBook(folder : Folder) {
        eglineDatabase.SavedBookDao().deleteSavedBooks(folderToSavedBook(folder))
        UserRepository.get().updateCurrent()
    }

    override suspend fun removeFolder(folder : Folder) {
        eglineDatabase.SavedBookDao().deleteFolder(folder.folderName)
        UserRepository.get().updateCurrent()
    }

    override suspend fun getAllFolders() : List<Folder> {
        return savedBooksToFolder(eglineDatabase.SavedBookDao().getAllSavedBooks())
    }

    override suspend fun getFolderByName(folderName : String) : Folder {
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
