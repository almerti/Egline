package almerti.egline.data.database.dao

import almerti.egline.data.database.model.Chapter
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ChapterDao {

    @Upsert
    suspend fun UpsertChapter(chapter: Chapter)

    @Delete
    suspend fun deleteChapter(chapter : Chapter)

    @Query("DELETE FROM chapter where id = :id")
    suspend fun deleteChapterById(id: Int)

    @Query("SELECT * FROM chapter WHERE book_id = :bookId")
    fun getAllChaptersToBook(bookId:Int): Flow<List<Chapter>>
    @Query("SELECT * FROM chapter WHERE id = :id")
    suspend fun getChapter(id: Int): Chapter

    @Query("SELECT * FROM chapter WHERE book_id = :bookId AND number = :number ")
    suspend fun getChapterToBook(bookId:Int,number : Int): Chapter

}