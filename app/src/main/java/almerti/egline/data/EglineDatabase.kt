package almerti.egline.data

import almerti.egline.data.dao.BookDao
import almerti.egline.data.dao.ChapterDao
import almerti.egline.data.model.Book
import almerti.egline.data.model.Chapter
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