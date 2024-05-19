package almerti.egline.data.repository

import almerti.egline.data.model.Chapter

interface ChapterRepository {
    suspend fun get(chapterId : Int) : Chapter
    suspend fun getAll(bookId : Int) : List<Chapter>
    suspend fun getAll() : List<Chapter>
    suspend fun getText(chapterId : Int) : String
    suspend fun getText(chapter : Chapter) : String
    suspend fun getAudio(chapterId : Int) : String
    suspend fun getAudio(chapter : Chapter) : String


}
