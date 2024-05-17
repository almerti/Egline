package almerti.egline.data.implementation

import almerti.egline.data.model.User
import almerti.egline.data.repository.FolderRepository
import almerti.egline.data.repository.UserRepository
import almerti.egline.data.source.network.NetworkApi
import almerti.egline.data.source.network.model.UserLogin
import androidx.datastore.core.DataStore
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.util.logging.Logger
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(

    private val remoteApi : NetworkApi,
    private val userDataStore : DataStore<User>,
    private val folderRepository : FolderRepository
) : UserRepository {
    override suspend fun get() : Flow<User> {
        networkUpdate()
        return userDataStore.data
    }

    override suspend fun update(user : User) {
        try {
            userDataStore.updateData {
                user
            }

            val networkUser = userToNetworkUser(user)
            val savedBooks = JsonObject()
            val gson = Gson()

            folderRepository.getAll().forEach {
                savedBooks.addProperty(it.folderName, gson.toJson(it.bookIds))
            }
            networkUser.savedBooks = savedBooks

            Logger.getGlobal().info(networkUser.toString())
            remoteApi.updateUser(networkUser.id, networkUser)

        } catch (e : Exception) {
            Logger.getGlobal().info(e.toString())
        }
    }

    override suspend fun update() {
        update(userDataStore.data.first())
    }

    override suspend fun get(userId : Int) : User {
        try {
            val userData = remoteApi.getUser(userId)
            if (userData.isSuccessful)
                return networkUserToUser(userData.body()!!)
            else
                return userDataStore.data.first()
        } catch (e : Exception) {
            return userDataStore.data.first()
        }
    }

    override suspend fun register(user : User) : String {
        try {

            val answer = remoteApi.createUser(userToNetworkUser(user))
            if (answer.isSuccessful) {
                login(user.email, user.password!!)
                return "OK"
            } else return answer.errorBody()?.string() ?: "No response"
        } catch (e : Exception) {
            Logger.getGlobal().info(e.toString())
            return "No response"
        }
    }

    override suspend fun login(email : String, password : String) : String {
        try {
            val response = remoteApi.login(UserLogin(email = email, password = password))
            if (response.isSuccessful) {
                val user = networkUserToUser(response.body()!!)
                userDataStore.updateData {
                    user
                }

                return "OK"
            } else {
                return response.errorBody()?.string() ?: "No response"
            }
        } catch (e : Exception) {
            Logger.getGlobal().info(e.toString())
            return "No response"
        }
    }

    override suspend fun delete(userId: Int): String {
        val answer = remoteApi.deleteUser(userDataStore.data.first().id)

        if (answer.isSuccessful) {
            userDataStore.updateData {
                User()
            }
            folderRepository.removeAll()
            return "OK"
        } else return answer.errorBody()?.string() ?: "No response"
    }


    override suspend fun networkUpdate() {
        try {
            val networkUser = remoteApi.getUser(userDataStore.data.first().id)
            if (networkUser.isSuccessful && networkUser.body() != null) {
                val user = networkUserToUser(networkUser.body()!!)
                if (user != userDataStore.data.first())
                    userDataStore.updateData {
                        user
                    }
            }
        } catch (e : Exception) {
            Logger.getGlobal().info(e.toString())
        }
    }

    private suspend fun networkUserToUser(user : almerti.egline.data.source.network.model.User) : User {

        folderRepository.saveFoldersJson(user.savedBooks)

        return User(
            id = user.id,
            email = user.email,
            displayName = user.displayName,
            avatar = user.avatar,
            password = user.password,
        )
    }

    private fun userToNetworkUser(user: User): almerti.egline.data.source.network.model.User {

        return almerti.egline.data.source.network.model.User(
            id = user.id,
            email = user.email,
            displayName = user.displayName,
            avatar = user.avatar,
            password = user.password ?: "",
            savedBooks = JsonObject(),
        )
    }
}
