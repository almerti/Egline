package almerti.egline.data.source.database

import almerti.egline.data.source.database.dao.BookDao
import almerti.egline.data.source.database.dao.ChapterDao
import almerti.egline.data.source.database.dao.SavedBookDao
import almerti.egline.data.source.database.model.Book
import almerti.egline.data.source.database.model.Chapter
import almerti.egline.data.source.database.model.SavedBook
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Book::class,
        Chapter::class,
        SavedBook::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class EglineDatabase : RoomDatabase() {
    abstract fun BookDao() : BookDao
    abstract fun ChapterDao() : ChapterDao
    abstract fun SavedBookDao() : SavedBookDao

}
