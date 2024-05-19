package almerti.egline.data.source.database.dao

import almerti.egline.data.source.database.model.SavedBook
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedBookDao {
    @Upsert
    suspend fun upsertSavedBook(savedBook : SavedBook)

    @Upsert
    suspend fun upsertSavedBooks(savedBooks : List<SavedBook>)

    @Delete
    suspend fun deleteSavedBook(savedBook : SavedBook)

    @Delete
    suspend fun deleteSavedBooks(savedBook : List<SavedBook>)

    @Query("UPDATE saved_books SET folder_name = :newFolderName WHERE folder_name = :oldFolderName")
    suspend fun updateFolderName(oldFolderName : String, newFolderName : String)

    @Query(value = "DELETE FROM saved_books WHERE folder_name = :folderName")
    suspend fun deleteFolder(folderName : String)

    @Query("SELECT * FROM saved_books")
    fun getAllSavedBooks() : Flow<List<SavedBook>>

    @Query("SELECT * FROM saved_books WHERE folder_name = :name")
    fun getSavedBooksByFolderName(name : String) : Flow<List<SavedBook>>

    @Query("SELECT folder_name FROM saved_books WHERE book_id = :bookId")
    fun getFolderNamedByBookId(bookId : Int) : Flow<List<String>>

    @Query(value = "DELETE FROM saved_books")
    suspend fun deleteAllSavedBooks()
}
