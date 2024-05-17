package almerti.egline.data.repository

import almerti.egline.data.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    suspend fun getAll() : Flow<MutableList<Book>>
    suspend fun getById(id : Int) : Book?
    suspend fun getByName(name : String) : List<Book>
    suspend fun update()
    suspend fun removeAll()
}
