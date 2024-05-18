package almerti.egline.data.repository

import almerti.egline.data.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    @Deprecated(message = "Use getFlow() instead", replaceWith = ReplaceWith("getFlow()"))
    suspend fun getAll() : List<Book>

    suspend fun getFlow() : Flow<List<Book>>
    suspend fun getNext(amount : Int)
    suspend fun getById(id : Int) : Book?
    suspend fun getByName(name : String) : List<Book>
    suspend fun getSaved() : Flow<List<Book>>

    @Deprecated("Send high latency request")
    suspend fun update()
    suspend fun removeAll()
}
