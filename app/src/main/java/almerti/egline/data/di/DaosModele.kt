package almerti.egline.data.di

import almerti.egline.data.EglineDatabase
import almerti.egline.data.dao.BookDao
import almerti.egline.data.dao.ChapterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModele{
    @Provides
    fun providesBookDao(
        database:EglineDatabase
    ): BookDao =database.BookDao()

    @Provides
    fun providesChapterDao(
        database:EglineDatabase
    ): ChapterDao =database.ChapterDao()
}