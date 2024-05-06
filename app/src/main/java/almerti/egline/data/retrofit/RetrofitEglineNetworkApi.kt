package almerti.egline.data.retrofit

import almerti.egline.data.retrofit.model.Book
import android.net.NetworkRequest
import retrofit2.http.GET

interface RetrofitEglineNetworkApi {
    @GET("Books/GetBookList")
    suspend fun Getbooks():NetworkRequest<List<Book>>
}