package almerti.egline.data.model

data class User(
    val id : Int,
    val displayName : String,
    val email : String,
    val avatar : ByteArray,
) {
    override fun equals(other : Any?) : Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        return id == other.id
    }

    override fun hashCode() : Int {
        var result = id
        result = 31 * result + displayName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + avatar.contentHashCode()
        return result
    }
}
