package almerti.egline.data.source.database.di

import almerti.egline.data.source.database.EglineDatabase
import almerti.egline.data.source.database.dao.BookDao
import almerti.egline.data.source.database.dao.ChapterDao
import almerti.egline.data.source.database.dao.SavedBookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModele {
    @Provides
    fun providesBookDao(
        database : EglineDatabase
    ) : BookDao = database.BookDao()

    @Provides
    fun providesChapterDao(
        database : EglineDatabase
    ) : ChapterDao = database.ChapterDao()

    @Provides
    fun providesSavedBookDao(
        database : EglineDatabase
    ) : SavedBookDao = database.SavedBookDao()
}
