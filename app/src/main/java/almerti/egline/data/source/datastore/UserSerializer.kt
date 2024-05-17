package almerti.egline.data.source.datastore

import almerti.egline.data.model.User
import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object UserSerializer : Serializer<User> {
    override val defaultValue : User
        get() = User()

    override suspend fun readFrom(input : InputStream) : User {
        return try {
            Json.decodeFromString(
                User.serializer(),
                input.readBytes().decodeToString(),
            )
        } catch (e : SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t : User, output : OutputStream) {
        return withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    User.serializer(),
                    t,
                ).encodeToByteArray(),
            )
        }
    }

}
