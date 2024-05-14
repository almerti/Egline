package almerti.egline.data.source.database.dao

import almerti.egline.data.source.database.model.Book
import almerti.egline.data.source.database.model.Status
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Upsert
    suspend fun UpsertBook(book : Book)

    @Delete
    suspend fun DeleteBook(book : Book)

    @Query("SELECT * FROM Book")
    fun GetAllBooks() : Flow<List<Book>>

    @Query("SELECT * FROM Book WHERE id = :id")
    suspend fun GetBookById(id : Int) : Book?

    @Query(
        """
    SELECT * FROM Book
    WHERE
        CASE WHEN :useStatusFilter THEN status = :status END
        AND
        CASE WHEN :useDateFilter THEN date BETWEEN :startDate AND :endDate END
        AND
        CASE WHEN :useRatingFilter THEN rating > :minRating AND rating < :maxRating END
""",
    )
    fun getBooksByFilters(
        useStatusFilter : Boolean = false,
        status : Status? = null,
        useDateFilter : Boolean = false,
        startDate : Long? = null,
        endDate : Long? = null,
        useRatingFilter : Boolean = false,
        minRating : Int? = null,
        maxRating : Int? = null,
    ) : Flow<List<Book>>

}
