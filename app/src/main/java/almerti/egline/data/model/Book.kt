package almerti.egline.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id:Int ,
    val title:String ,
    val description:String ,
    val cover:ByteArray ,
    val rating:Double,
    @ColumnInfo(name = "rating_count")
    val ratingCount:Int,
    val views:Int,
    val date:Long,
    val status:Status

    
)

enum class Status{
    ONGOING ,
    COMPLETED ,
    CANCELLED

}