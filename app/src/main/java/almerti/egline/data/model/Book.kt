package almerti.egline.data.model

data class Book(
    val id : Int,
    val title : String,
    val description : String,
    val cover : ByteArray,
    val rating : Double,
    val ratingCount : Int,
    val views : Int,
    val date : Long,
    val status : String
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
        if (date != other.date) return false
        if (status != other.status) return false

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
        result = 31 * result + date.hashCode()
        result = 31 * result + status.hashCode()
        return result
    }
}
