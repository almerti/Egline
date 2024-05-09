package almerti.egline.data.network

import almerti.egline.data.network.model.Book
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitEglineNetworkApi {
    @GET("Books/GetBookList")
    suspend fun Getbooks():Response<List<Book>>
}