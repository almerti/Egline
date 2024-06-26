package almerti.egline.data.source.database.dao

import almerti.egline.data.model.Book
import almerti.egline.data.model.Status
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Upsert
    suspend fun upsertBook(book : Book)

    @Upsert
    suspend fun upsertBooks(books : List<Book>)

    @Delete
    suspend fun deleteBook(book : Book)

    @Query("SELECT * FROM Book")
    fun getAllBooks() : Flow<List<Book>>

    @Query("SELECT * FROM Book WHERE id = :id")
    suspend fun getBookById(id : Int) : Book?

    @Query(
        """
    SELECT * FROM Book
    WHERE
        CASE WHEN :useStatusFilter THEN status = :status END
        AND
        CASE WHEN :useDateFilter THEN year BETWEEN :startDate AND :endDate END
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

    @Query("DELETE FROM Book")
    suspend fun deleteAllBooks()

    @Query("DELETE FROM Book WHERE id = :id")
    suspend fun deleteBookById(id : Int)

    @Query("SELECT * FROM Book WHERE title LIKE '%' || :name || '%'")
    suspend fun getBookByName(name : String) : List<Book>
}
