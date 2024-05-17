package almerti.egline.data.repository

import almerti.egline.data.model.Book
import almerti.egline.data.model.Comment

interface CommentRepository {
    suspend fun getAll() : List<Comment>
    suspend fun getAll(book : Book) : List<Comment>
    suspend fun getAll(bookId : Int) : List<Comment>
    suspend fun add(comment : Comment)
    suspend fun delete(comment : Comment)
    suspend fun update(comment : Comment)
}
