package almerti.egline.data.source.datastore.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id : Int = -1,
    val displayName : String = "Unknown",
    val email : String = "Unknown",
    val avatar : ByteArray = ByteArray(0),
)
