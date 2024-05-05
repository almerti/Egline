package almerti.egline.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = Book::class, parentColumns = ["id"], childColumns = ["bookId"])],
    indices = [Index("bookId")])
data class Chapter(
    @PrimaryKey(autoGenerate = true)
    val id: Int ,
    val bookId: Int ,
    val chapterName: String ,
    val chapterNumber : Number,
    val chapterTextContent: String,
    val chapterAudioContent: String,
    val date: Long
)

