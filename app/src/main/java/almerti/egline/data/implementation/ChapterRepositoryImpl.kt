package almerti.egline.data.implementation

import almerti.egline.data.model.Chapter
import almerti.egline.data.repository.ChapterRepository
import almerti.egline.data.source.database.EglineDatabase
import almerti.egline.data.source.network.NetworkApi
import android.util.Log
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ChapterRepositoryImpl @Inject constructor(
    private val remoteApi : NetworkApi,
    private val eglineDatabase : EglineDatabase
) : ChapterRepository {
    override suspend fun get(chapterId : Int) : Chapter {
        try {
            val response = remoteApi.getChapterByBookId(chapterId)
            if (response.isSuccessful && response.body() != null) {
                val chapter = networkToModel(response.body()!!)

                return chapter
            }
        } catch (e : Exception) {
            Log.e("ChapterRepositoryImpl", e.toString())
        }
        return entityToModel(eglineDatabase.ChapterDao().getChapter(chapterId))
    }

    override suspend fun getAll(bookId : Int) : List<Chapter> {
        try {
            val response = remoteApi.getAllChaptersToBookId(bookId)
            if (response.isSuccessful && response.body() != null) {
                val chapters = response.body()!!.map {networkToModel(it)}

                return chapters
            }
        } catch (e : Exception) {
            Log.e("ChapterRepositoryImpl", e.toString())
        }
        return eglineDatabase.ChapterDao().getAllChaptersToBook(bookId).first()
            .map {entityToModel(it)}
    }

    override suspend fun getAll() : List<Chapter> {
        try {
            val response = remoteApi.getChapters()
            if (response.isSuccessful && response.body() != null) {
                val chapters = response.body()!!.map {networkToModel(it)}
                return chapters
            }
        } catch (e : Exception) {
            Log.e("ChapterRepositoryImpl", e.toString())
        }
        return eglineDatabase.ChapterDao().getAllChapters().map {entityToModel(it)}
    }

    override suspend fun getText(chapterId : Int) : String {
        try {
            val response = remoteApi.getChapterTextContent(chapterId)
            if (response.isSuccessful && response.body() != null) {
                return response.body()!!
            }
            return eglineDatabase.ChapterDao().getChapter(chapterId).textContent
        } catch (e : Exception) {
            Log.e("ChapterRepositoryImpl", e.toString())
        }
        return "Text not found"
    }

    override suspend fun getText(chapter : Chapter) : String {
        return getText(chapter.id)
    }

    override suspend fun getAudio(chapterId : Int) : String {
        try {
            val response = remoteApi.getChapterAudioContent(chapterId)
            if (response.isSuccessful && response.body() != null) {
                return response.body()!!
            }
        } catch (e : Exception) {
            Log.e("ChapterRepositoryImpl", e.toString())
        }
        return "Error 500: Audio not found"
    }

    override suspend fun getAudio(chapter : Chapter) : String {
        return getAudio(chapter.id)
    }

    override suspend fun saveToDb(chapter : Chapter) {
        eglineDatabase.ChapterDao().upsertChapter(modelToEntity(chapter))
    }

    override suspend fun saveToDb(chapterList : List<Chapter>) {
        eglineDatabase.ChapterDao().upsertChapters(chapterList.map {modelToEntity(it)})
    }

    override suspend fun saveToDb(chapterId : Int) {
        saveToDb(get(chapterId))
    }


    private fun entityToModel(entity : almerti.egline.data.source.database.model.Chapter) : Chapter {
        return Chapter(
            id = entity.id,
            bookId = entity.bookId,
            title = entity.name,
            number = entity.number,
            textContent = entity.textContent,
            date = LocalDate.ofEpochDay(entity.date),
        )
    }

    private fun modelToEntity(model : Chapter) : almerti.egline.data.source.database.model.Chapter {
        return almerti.egline.data.source.database.model.Chapter(
            id = model.id,
            bookId = model.bookId,
            date = model.date.toEpochDay(),
            name = model.title,
            number = model.number,
            textContent = model.textContent,
        )
    }


    private suspend fun networkToModel(netChapter : almerti.egline.data.source.network.model.Chapter) : Chapter {

        return Chapter(
            id = netChapter.id,
            bookId = netChapter.bookId,
            date = parseLocalDate(netChapter.date),
            title = netChapter.title,
            number = netChapter.number,
            textContent = getText(netChapter.id),
        )
    }

    private fun parseLocalDate(dateString : String) : LocalDate {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(dateString, formatter)
    }
}
