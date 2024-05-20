package almerti.egline.data.implementation

import almerti.egline.data.model.Book
import almerti.egline.data.model.Status
import almerti.egline.data.repository.BookRepository
import almerti.egline.data.source.database.EglineDatabase
import almerti.egline.data.source.network.NetworkApi
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val remoteApi : NetworkApi,
    private val eglineDatabase : EglineDatabase,
) : BookRepository {

    private var bookIdx = mutableListOf<Int>()

    @Deprecated("Use getFlow() instead", replaceWith = ReplaceWith("getFlow()"))
    override suspend fun getAll() : List<Book> {
        update()
        return eglineDatabase.BookDao().getAllBooks().first()
    }

    override suspend fun getFlow() : Flow<List<Book>> {
        try {
            val response = remoteApi.getIdsBooks()
            if (response.isSuccessful && response.body() != null) {
                bookIdx = response.body()!!.toMutableList()
            }
        } catch (E : Exception) {
            Log.e("BookRepositoryImpl", E.toString())
        }
        return eglineDatabase.BookDao().getAllBooks()
    }

    override suspend fun getNext(amount : Int) {
        for (i in 1..amount) {
            try {
                if (bookIdx.isEmpty()) break

                val response = remoteApi.getBook(bookIdx.first())
                bookIdx.removeAt(0)
                if (response.isSuccessful) {
                    val book = networkToModel(response.body()!!)
                    eglineDatabase.BookDao().upsertBook(book)
                }
            } catch (E : Exception) {
                Log.e("BookRepositoryImpl", E.toString())
            }
        }
    }

    override suspend fun clearFlow() {
        eglineDatabase.BookDao().deleteAllBooks()
    }

    override suspend fun getById(id : Int) : Book? {
        try {
            val response = remoteApi.getBook(id)
            if (response.isSuccessful) {
                val book = networkToModel(response.body()!!)
                eglineDatabase.BookDao().upsertBook(book)
                return book
            }
        } catch (e : Exception) {
            Log.e("BookRepositoryImpl", e.toString())
        }
        return eglineDatabase.BookDao().getBookById(id)

        //        return withContext(Dispatchers.IO) {
        //             try {
        //                 val response = remoteApi.getBook(id)
        //                 if (response.isSuccessful) {
        //                     val book = networkToModel(response.body()!!)
        //                     eglineDatabase.BookDao().upsertBook(book)
        //                     return@withContext book
        //                 }
        //             } catch (e : Exception) {
        //                 Log.e("BookRepositoryImpl", e.toString())
        //             }
        //             return@withContext eglineDatabase.BookDao().getBookById(id)
        //         }

    }

    override suspend fun getByName(name : String) : List<Book> {
        return eglineDatabase.BookDao().getBookByName(name)
    }

    override suspend fun getSaved() : Flow<List<Book>> {
        return eglineDatabase.BookDao().getAllBooks()
    }

    @Deprecated("Send high latency request")
    override suspend fun update() {
        try {
            val response = remoteApi.getBooks()
            if (response.isSuccessful) {
                val books = response.body()!!.map {networkToModel(it)}
                eglineDatabase.BookDao().upsertBooks(books)
            }
        } catch (e : Exception) {
            Log.e("BookRepositoryImpl", e.toString())
        }
    }

    override suspend fun removeAll() {
        eglineDatabase.BookDao().deleteAllBooks()
    }


    private fun parseStatus(status : String) : Status {
        return when (status) {
            "COMPLETED" -> Status.COMPLETED
            "ONGOING" -> Status.ONGOING
            "CANCELLED" -> Status.CANCELLED
            else -> Status.UNKNOWN
        }
    }

    private fun networkToModel(book : almerti.egline.data.source.network.model.Book) : Book {
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
