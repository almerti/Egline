package almerti.egline.data.source.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "saved_books", primaryKeys = ["book_id", "folder_name"])
data class SavedBook(
    @ColumnInfo(name = "book_id")
    val bookId : Int,
    @ColumnInfo(name = "folder_name")
    val folderName : String
)
