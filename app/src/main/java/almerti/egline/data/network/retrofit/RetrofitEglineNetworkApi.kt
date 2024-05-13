package almerti.egline.data.network.retrofit

import almerti.egline.data.network.NetworkApi
import almerti.egline.data.network.model.Book
import almerti.egline.data.network.model.BookRate
import almerti.egline.data.network.model.Chapter
import almerti.egline.data.network.model.Comment
import almerti.egline.data.network.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RetrofitEglineNetworkApi : NetworkApi {
    //Book
    @GET(value = "book")
    override suspend fun getBooks() : Response<List<Book>>

    @GET(value = "book/{id}")
    override suspend fun getBook(@Path("id") id : Number) : Response<Book>

    @POST(value = "book/rate")
    override suspend fun addRateToBook(@Body bookRate : BookRate) : Response<String>

    @PUT(value = "book/rate")
    override suspend fun updateRateToBook(@Body bookRate : BookRate) : Response<String>

    @DELETE(value = "book/rate/{book-id}/{user-id}")
    override suspend fun deleteRateToBook(
        @Path(value = "book-id") bookId : Int ,
        @Path(value = "user-id") userId : Int
    ) : Response<String>


    //User
    @GET(value = "user/{id}")
    override suspend fun getUser(@Path("id") id : Number) : Response<User>

    @POST(value = "user")
    override suspend fun createUser(@Body user : User) : Response<String>

    @PUT(value = "user/{id}")
    override suspend fun updateUser(@Path("id") id : Number , @Body user : User) : Response<String>

    @DELETE(value = "user/{id}")
    override suspend fun deleteUser(@Path("id") id : Number) : Response<String>


    //Chapter
    @GET(value = "chapter")
    override suspend fun getChapters() : Response<List<Chapter>>

    @GET(value = "chapter/{id}")
    override suspend fun getChapterByBookId(@Path("id") id : Number) : Response<Chapter>

    @GET(value = "chapter/Chapter/book-chapters/{id}")
    override suspend fun getAllChaptersToBookId(@Path("id") id : Number) : Response<List<Chapter>>


    //comment
    @GET(value = "comment")
    override suspend fun getComments() : Response<List<Comment>>

    @GET(value = "comment/{id}")
    override suspend fun getComment(@Path("id") id : Number) : Response<Comment>

    @POST(value = "comment")
    override suspend fun createComment(@Body comment : Comment) : Response<String>

    @PUT(value = "comment/{id}")
    override suspend fun updateComment(
        @Path("id") id : Number ,
        @Body comment : Comment
    ) : Response<String>
}