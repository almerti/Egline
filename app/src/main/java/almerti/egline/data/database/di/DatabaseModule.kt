package almerti.egline.data.database.di

import almerti.egline.data.database.EglineDatabase
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule{

    @Provides
    @Singleton
    fun ProvidesEglineDatabase(
        @ApplicationContext context: Context
    ): EglineDatabase = Room.databaseBuilder(
        context,
        EglineDatabase::class.java,
        "egline.db")
        .build()
}