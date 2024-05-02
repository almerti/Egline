package almerti.egline.data.book

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey
    val id:Int ,
    val title:String ,
    val description:String ,
    val cover:ByteArray ,
    val rating:Double
    
)