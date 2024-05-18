package almerti.egline.data.implementation

import almerti.egline.data.model.Book
import almerti.egline.data.model.Status
import almerti.egline.data.repository.BookRepository
import almerti.egline.data.source.database.EglineDatabase
import almerti.egline.data.source.network.NetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.logging.Logger
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val remoteApi: NetworkApi,
    private val EglineDatabase: EglineDatabase,
) : BookRepository {

    override suspend fun getAll(): Flow<MutableList<Book>> {
        update()
        return EglineDatabase.BookDao().getAllBooks().map {
            it.toMutableList()
        }
    }

    override suspend fun getById(id: Int): Book? {
        val response = remoteApi.getBook(id)
        if (response.isSuccessful) {
            val book = networkToModel(response.body()!!)
            EglineDatabase.BookDao().upsertBook(book)
            return book
        }
        return EglineDatabase.BookDao().getBookById(id)
    }

    override suspend fun getByName(name: String): List<Book> {
        update()
        return EglineDatabase.BookDao().getBookByName(name)
    }

    override suspend fun update() {
        try {
            val response = remoteApi.getBooks()
            if (response.isSuccessful) {
                val books = response.body()!!.map {networkToModel(it)}
                EglineDatabase.BookDao().upsertBooks(books)
            }
        } catch (e: Exception) {
            Logger.getGlobal().info(e.toString())
        }
    }

    override suspend fun removeAll() {
        EglineDatabase.BookDao().deleteAllBooks()
    }


    private fun parseStatus(status: String): Status {
        return when (status) {
            "COMPLETED" -> Status.COMPLETED
            "ONGOING" -> Status.ONGOING
            "CANCELLED" -> Status.CANCELLED
            else -> Status.UNKNOWN
        }
    }

    private fun networkToModel(book: almerti.egline.data.source.network.model.Book): Book {
        return Book(
            id = book.id,
            title = book.title,
            description = book.description,
            cover = book.cover,
            rating = book.rating,
            ratingCount = book.rates,
            views = book.views,
            year = book.year,
            status = parseStatus(book.status),
            genres = book.genres,
        )
    }
}
