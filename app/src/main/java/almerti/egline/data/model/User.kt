package almerti.egline.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id : Int = -1,
    val displayName : String = "Unknown",
    val email : String = "Unknown",
    val avatar : ByteArray = ByteArray(0),
    val password : String? = null
) {
    override fun equals(other : Any?) : Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (displayName != other.displayName) return false
        if (email != other.email) return false
        if (!avatar.contentEquals(other.avatar)) return false

        return true
    }

    override fun hashCode() : Int {
        var result = id
        result = 31 * result + displayName.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + avatar.contentHashCode()
        return result
    }
}
