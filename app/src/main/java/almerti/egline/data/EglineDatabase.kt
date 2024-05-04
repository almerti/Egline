package almerti.egline.data

import almerti.egline.data.dao.BookDao
import almerti.egline.data.model.Book
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Book::class],
    version = 1
)
abstract class EglineDatabase:RoomDatabase() {
    abstract val dao: BookDao
}