package almerti.egline.data.repository

import almerti.egline.data.model.Chapter
import kotlinx.coroutines.flow.Flow

interface ChapterRepository {
    suspend fun get(chapterId : Int) : Chapter
    suspend fun getAll(bookId : Int) : Flow<MutableList<Chapter>>
    suspend fun getAll() : Flow<MutableList<Chapter>>
    suspend fun getText(chapterId : Int) : String
    suspend fun getText(chapter : Chapter) : String
    suspend fun getAudio(chapterId : Int) : ByteArray?
    suspend fun getAudio(chapter : Chapter) : ByteArray?


}
