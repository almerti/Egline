package almerti.egline.data.dao

import almerti.egline.data.model.Chapter
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
@Dao
interface ChapterDao {
    @Query("SELECT * FROM chapter WHERE id = :id")
   suspend fun getChapter(id: Int): Chapter
    @Query("SELECT * FROM chapter")
    suspend fun getAllChapters(): List<Chapter>

    @Upsert
    suspend fun UpsertChapter(chapter: Chapter)

    @Delete
    suspend fun deleteChapter(chapter : Chapter)

    @Query("DELETE FROM chapter where id = :id")
    suspend fun deleteChapterById(id: Int)

}