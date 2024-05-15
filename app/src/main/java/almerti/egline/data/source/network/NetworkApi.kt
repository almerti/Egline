package almerti.egline.data.source.network

import almerti.egline.data.source.network.model.Book
import almerti.egline.data.source.network.model.BookRate
import almerti.egline.data.source.network.model.Chapter
import almerti.egline.data.source.network.model.Comment
import almerti.egline.data.source.network.model.User
import almerti.egline.data.source.network.model.UserLogin
import retrofit2.Response

interface NetworkApi {
    // Book
    suspend fun getBooks() : Response<List<Book>>
    suspend fun getBook(id : Number) : Response<Book>
    suspend fun addRateToBook(bookRate : BookRate) : Response<String>
    suspend fun updateRateToBook(bookRate : BookRate) : Response<String>
    suspend fun deleteRateToBook(bookId : Int, userId : Int) : Response<String>


    // User
    suspend fun getUser(id : Number) : Response<User>
    suspend fun createUser(user : User) : Response<String>
    suspend fun updateUser(id : Number, user : User) : Response<String>
    suspend fun deleteUser(id : Number) : Response<String>
    suspend fun login(userLogin : UserLogin) : Response<String>

    // Chapter
    suspend fun getChapters() : Response<List<Chapter>>
    suspend fun getChapterByBookId(id : Number) : Response<Chapter>
    suspend fun getAllChaptersToBookId(id : Number) : Response<List<Chapter>>

    // Comment
    suspend fun getComments() : Response<List<Comment>>
    suspend fun getComment(id : Number) : Response<Comment>
    suspend fun createComment(comment : Comment) : Response<String>
    suspend fun updateComment(id : Number, comment : Comment) : Response<String>
}
