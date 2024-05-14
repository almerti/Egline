package almerti.egline.data.repository

import almerti.egline.data.model.User
import almerti.egline.data.source.network.NetworkApi
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteApi : NetworkApi,
    private val userDataStore : DataStore<almerti.egline.data.source.datastore.model.User>,
) : UserRepository {
    override suspend fun getCurrentUser() : Flow<User> {

        return userDataStore.data.map {
            toAppUser(it)
        }
    }

    override suspend fun updateCurrentUser(user : User) {
        userDataStore.updateData {
            toDataStoreUser(user)
        }
    }

    override suspend fun getOtherUser(userId : Int) : User {
        TODO("Not yet implemented")
    }

    override suspend fun CreateUser(user : User) {
        TODO("Not yet implemented")
    }

    override suspend fun DeleteUser(userId : Int) {
        TODO("Not yet implemented")
    }

    fun toAppUser(user : almerti.egline.data.source.datastore.model.User) : User {
        return User(
            id = user.id,
            displayName = user.displayName,
            email = user.email,
            avatar = user.avatar,
            password = "",
        )
    }

    fun toDataStoreUser(user : User) : almerti.egline.data.source.datastore.model.User {
        return almerti.egline.data.source.datastore.model.User(
            id = user.id,
            displayName = user.displayName,
            email = user.email,
            avatar = user.avatar,
        )
    }

}
