package almerti.egline.data.network

import almerti.egline.data.network.model.Book
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitEglineNetworkApi {
    @GET("5026f982-7bd2-4058-b349-4115c1dbd093?api_key=hFJK9n8TzHp5_pyrpyiO6w")
//    @GET("Books/GetBookList")
    suspend fun GetBooks():Response<Book>
}