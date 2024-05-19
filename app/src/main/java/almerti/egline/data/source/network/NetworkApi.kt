package almerti.egline.data.source.network

import almerti.egline.data.source.network.model.Book
import almerti.egline.data.source.network.model.BookRate
import almerti.egline.data.source.network.model.Chapter
import almerti.egline.data.source.network.model.Comment
import almerti.egline.data.source.network.model.User
import almerti.egline.data.source.network.model.UserEdit
import almerti.egline.data.source.network.model.UserLogin
import retrofit2.Response

interface NetworkApi {
    // Book
    suspend fun getBooks() : Response<List<Book>>
    suspend fun getIdsBooks() : Response<List<Int>>
    suspend fun getBook(id : Int) : Response<Book>
    suspend fun addRateToBook(bookRate : BookRate) : Response<String>
    suspend fun updateRateToBook(bookRate : BookRate) : Response<String>
    suspend fun deleteRateToBook(bookId : Int, userId : Int) : Response<String>


    // User
    suspend fun getUser(id : Int) : Response<User>
    suspend fun createUser(user : User) : Response<String>
    suspend fun updateUser(id : Int, user : User) : Response<User>
    suspend fun deleteUser(id : Int) : Response<String>
    suspend fun login(userLogin : UserLogin) : Response<User>
    suspend fun edit(id : Int, userEdit : UserEdit) : Response<User>

    // Chapter
    suspend fun getChapters() : Response<List<Chapter>>
    suspend fun getChapterByBookId(id : Int) : Response<Chapter>
    suspend fun getAllChaptersToBookId(id : Int) : Response<List<Chapter>>
    suspend fun getChapterTextContent(id : Int) : Response<String>
    suspend fun getChapterAudioContent(id : Int) : Response<String>

    // Comment
    suspend fun getComments() : Response<List<Comment>>
    suspend fun getComment(id : Int) : Response<Comment>
    suspend fun createComment(comment : Comment) : Response<String>
    suspend fun deleteComment(commentId : Int) : Response<String>
    suspend fun updateComment(id : Int, comment : Comment) : Response<String>
}
