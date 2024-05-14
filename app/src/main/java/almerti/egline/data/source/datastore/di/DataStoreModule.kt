package almerti.egline.data.source.datastore.di

import almerti.egline.data.source.datastore.UserSerializer
import almerti.egline.data.source.datastore.model.User
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {


    @Provides
    @Singleton
    internal fun providesUserSerializer() : UserSerializer =
        UserSerializer

    @Provides
    @Singleton
    internal fun providesUserDataStore(
        @ApplicationContext context : Context,
        userSerializer : UserSerializer,
    ) : DataStore<User> =
        DataStoreFactory.create(
            serializer = userSerializer,
        ) {
            context.dataStoreFile("user_info.pb")
        }
}
