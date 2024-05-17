package almerti.egline.data.repository

import almerti.egline.data.model.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    suspend fun getAll() : Flow<MutableList<Book>>
    suspend fun getById(id : Int) : Flow<Book>
    suspend fun getByName(name : String) : Flow<MutableList<Book>>
}
