package almerti.egline.data.network.di


import almerti.egline.data.network.NetworkApi
import almerti.egline.data.network.retrofit.RetrofitEglineNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun okHttpCallClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                },
            )


            .build();
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.113:8000/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideNetworkApi(retrofit : Retrofit) : NetworkApi {
        return retrofit.create(RetrofitEglineNetworkApi::class.java)
    }

}
