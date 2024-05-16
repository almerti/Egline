package almerti.egline.data.repository

import almerti.egline.data.model.Book
import almerti.egline.data.model.Comment
import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    suspend fun getAll() : Flow<MutableList<Comment>>
    suspend fun getAll(book : Book) : Flow<MutableList<Comment>>
    suspend fun add(comment : Comment)
    suspend fun delete(comment : Comment)
    suspend fun update(comment : Comment)
}
