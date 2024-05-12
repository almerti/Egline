package almerti.egline.data.network

import almerti.egline.data.network.model.Book
import almerti.egline.data.network.model.BookRate
import almerti.egline.data.network.model.Chapter
import almerti.egline.data.network.model.Comment
import almerti.egline.data.network.model.User
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RetrofitEglineNetworkApi {
    //Book
    @GET(value = "Book")
    suspend fun GetBooks() : Response<List<Book>>

    @GET(value = "Book/{id}")
    suspend fun GetBook(@Path("id") id : Number) : Response<Book>

    @POST(value = "Book/rate")
    suspend fun AddRateToBook(bookRate : BookRate) : Response<String>


    //User
    @GET(value = "user/{id}")
    suspend fun GetUsers(@Path("id") id : Number) : Response<User>

    @POST(value = "user")
    suspend fun CreateUser(user : User) : Response<String>

    @PUT(value = "user/{id}")
    suspend fun UpdateUser(@Path("id") id : Number , user : User) : Response<String>

    @DELETE(value = "user/{id}")
    suspend fun DeleteUser(@Path("id") id : Number) : Response<String>


    //Chapter
    @GET(value = "Chapter")
    suspend fun GetChapters() : Response<List<Chapter>>

    @GET(value = "Chapter/{id}")
    suspend fun GetChapterByBookId(@Path("id") id : Number) : Response<Chapter>

    @GET(value = "Chapter/Chapter/book-chapters/{id}")
    suspend fun GetAllChaptersToBookId(@Path("id") id : Number) : Response<List<Chapter>>


    //comment
    @GET(value = "comment")
    suspend fun GetComments() : Response<List<Comment>>

    @GET(value = "comment/{id}")
    suspend fun GetComment(@Path("id") id : Number) : Response<Comment>

    @POST(value = "comment")
    suspend fun CreateComment(comment : Comment) : Response<String>

    @PUT(value = "comment/{id}")
    suspend fun UpdateComment(@Path("id") id : Number , comment : Comment) : Response<String>
}