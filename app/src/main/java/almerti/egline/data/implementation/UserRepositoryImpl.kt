package almerti.egline.data.implementation

import almerti.egline.data.model.User
import almerti.egline.data.repository.FolderRepository
import almerti.egline.data.repository.UserRepository
import almerti.egline.data.source.network.NetworkApi
import almerti.egline.data.source.network.model.UserLogin
import android.util.Log
import androidx.datastore.core.DataStore
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(

    private val remoteApi : NetworkApi,
    private val userDataStore : DataStore<User>,
    private val folderRepository : FolderRepository
) : UserRepository {
    override suspend fun get() : Flow<User> {
        return userDataStore.data
    }

    override suspend fun sendDataToServer() {
        try {
            val user = userDataStore.data.first()
            val networkUser = userToNetworkUser(user)

            val savedBooks = JsonObject()
            val gson = Gson()
            folderRepository.getAll().collect {folderList ->
                folderList.forEach {
                    savedBooks.addProperty(it.folderName, gson.toJson(it.bookIds))
                }
            }
            networkUser.savedBooks = savedBooks

            remoteApi.updateUser(userDataStore.data.first().id, networkUser)
        } catch (e : Exception) {
            Log.e("UserRepositoryImpl", e.toString())
        }
    }

    override suspend fun get(userId : Int) : User? {
        try {
            val userData = remoteApi.getUser(userId)
            if (userData.isSuccessful && userData.body() != null) {
                return networkUserToUser(userData.body()!!)
            }
        } catch (e : Exception) {
            return null
        }
        return if (userDataStore.data.first().id == userId)
            userDataStore.data.first()
        else
            null
    }

    override suspend fun update(user : User) {
        userDataStore.updateData {
            user
        }
        sendDataToServer()
    }

    override suspend fun register(user : User) : String {
        try {
            val answer = remoteApi.createUser(userToNetworkUser(user))
            if (answer.isSuccessful) {
                login(user.email, user.password!!)
                return "OK"
            } else return answer.errorBody()?.string() ?: "No response"
        } catch (e : Exception) {
            Log.e("UserRepositoryImpl", e.toString())
            return "No response"
        }
    }

    override suspend fun login(email : String, password : String) : String {
        try {
            val response = remoteApi.login(UserLogin(email = email, password = password))

            if (response.isSuccessful && response.body() != null) {
                val user = networkUserToUser(response.body()!!)

                folderRepository.saveFoldersJson(response.body()!!.savedBooks)

                userDataStore.updateData {
                    user
                }

                return "OK"
            } else {
                return response.errorBody()?.string() ?: "No response"
            }
        } catch (e : Exception) {
            Log.e("UserRepositoryImpl", e.toString())
            return "No response"
        }
    }

    override suspend fun logout() {
        userDataStore.updateData {
            User()
        }
        folderRepository.removeAll()
    }

    override suspend fun delete(userId : Int) : String {
        val answer = remoteApi.deleteUser(userDataStore.data.first().id)

        if (answer.isSuccessful) {
            userDataStore.updateData {
                User()
            }
            folderRepository.removeAll()
            return "OK"
        } else return answer.errorBody()?.string() ?: "No response"
    }


    override suspend fun fetchDataFromServer() {
        try {
            val networkUser = remoteApi.getUser(userDataStore.data.first().id)
            if (networkUser.isSuccessful && networkUser.body() != null) {

                val user = networkUserToUser(networkUser.body()!!)
                folderRepository.saveFoldersJson(networkUser.body()!!.savedBooks)
                if (user != userDataStore.data.first())
                    userDataStore.updateData {
                        user
                    }
            }
        } catch (e : Exception) {
            Log.e("UserRepositoryImpl", e.toString())
        }
    }

    private fun networkUserToUser(user : almerti.egline.data.source.network.model.User) : User {
        return User(
            id = user.id,
            email = user.email,
            displayName = user.displayName,
            avatar = user.avatar,
            password = user.password,
        )
    }

    private fun userToNetworkUser(user : User) : almerti.egline.data.source.network.model.User {

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
