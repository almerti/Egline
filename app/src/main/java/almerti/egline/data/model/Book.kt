package almerti.egline.data.model

import almerti.egline.data.source.database.convertor.ListConvertor
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity
@TypeConverters(ListConvertor::class)
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val description : String,
    val cover : ByteArray,
    val rating : Double,
    @ColumnInfo(name = "rating_count")
    val ratingCount : Int,
    val views : Int,
    val year : Int,
    val status : Status,
    val genres : List<String>,
) {
    override fun equals(other : Any?) : Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (id != other.id) return false
        if (title != other.title) return false
        if (description != other.description) return false
        if (!cover.contentEquals(other.cover)) return false
        if (rating != other.rating) return false
        if (ratingCount != other.ratingCount) return false
        if (views != other.views) return false
        if (year != other.year) return false
        if (status != other.status) return false
        if (genres != other.genres) return false

        return true
    }

    override fun hashCode() : Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + cover.contentHashCode()
        result = 31 * result + rating.hashCode()
        result = 31 * result + ratingCount
        result = 31 * result + views
        result = 31 * result + year.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + genres.hashCode()

        return result
    }
}
