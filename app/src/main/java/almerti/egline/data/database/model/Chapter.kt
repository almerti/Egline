package almerti.egline.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = Book::class,
    parentColumns = ["id"],
    childColumns = ["book_id"])],
    indices = [Index("book_id")])
data class Chapter(
    @PrimaryKey(autoGenerate = true)
    val id: Int ,
    @ColumnInfo(name = "book_id")
    val bookId: Int ,
    val name: String ,
    val number : Int ,
    @ColumnInfo(name = "text_content")
    val textContent: String ,
    @ColumnInfo(name = "audio_content")
    val audioContent: String ,
    val date: Long
)