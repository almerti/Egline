package almerti.egline.data.model

import java.util.Date

data class Chapter(
    val id : Int,
    val bookId : Int,
    val title : String,
    val number : Int,
    val textContent : String,
    val audioContent : ByteArray?,
    val date : Date
) {
    override fun equals(other : Any?) : Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Chapter

        if (id != other.id) return false
        if (bookId != other.bookId) return false
        if (title != other.title) return false
        if (number != other.number) return false
        if (textContent != other.textContent) return false
        if (audioContent != null) {
            if (other.audioContent == null) return false
            if (!audioContent.contentEquals(other.audioContent)) return false
        } else if (other.audioContent != null) return false
        if (date != other.date) return false

        return true
    }

    override fun hashCode() : Int {
        var result = id
        result = 31 * result + bookId
        result = 31 * result + title.hashCode()
        result = 31 * result + number
        result = 31 * result + textContent.hashCode()
        result = 31 * result + (audioContent?.contentHashCode() ?: 0)
        result = 31 * result + date.hashCode()
        return result
    }
}
