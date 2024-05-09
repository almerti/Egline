package almerti.egline.data.network.di


import almerti.egline.data.network.RetrofitEglineNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule{

    @Provides
    @Singleton
    fun okHttpCallClient():OkHttpClient {
      return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build();
        }
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://random-data-api.com/api/v3/projects/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofitEglineNetworkApi(retrofit: Retrofit): RetrofitEglineNetworkApi {
        return retrofit.create(RetrofitEglineNetworkApi::class.java)
    }

}