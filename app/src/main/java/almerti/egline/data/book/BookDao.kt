package almerti.egline.data.book

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Upsert

@Dao
interface BookDao {

    @Upsert
    suspend fun UpsertBook(book : Book)

    @Delete
    suspend fun DeleteBook(book : Book)


}