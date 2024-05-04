package almerti.egline.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Book::class, parentColumns = ["id"], childColumns = ["book_id"])])
data class Chapter(
    @PrimaryKey(autoGenerate = true)
    val id: Int ,
    val bookId: Int ,
    val chapterId: Int ,
    val chapterName: String ,
    val chapterTextContent: String,
    val chapterAudioContent: String,
)

