package almerti.egline.data.database

import almerti.egline.data.database.dao.BookDao
import almerti.egline.data.database.dao.ChapterDao
import almerti.egline.data.database.model.Book
import almerti.egline.data.database.model.Chapter
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Book::class,
        Chapter::class],
    version = 1,
    exportSchema = false
)
abstract class EglineDatabase:RoomDatabase() {
    abstract fun BookDao(): BookDao
    abstract fun ChapterDao(): ChapterDao

}