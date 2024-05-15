package almerti.egline.data.repository

import almerti.egline.data.model.User
import almerti.egline.data.source.database.EglineDatabase
import almerti.egline.data.source.network.NetworkApi
import almerti.egline.data.source.network.model.UserLogin
import androidx.datastore.core.DataStore
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import retrofit2.HttpException
import java.util.logging.Logger
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteApi : NetworkApi,
    private val userDataStore : DataStore<User>,
    private val FolderRepository : FolderRepository
) : UserRepository {
    override suspend fun getCurrent() : Flow<User> {
        val networkUser = remoteApi.getUser(userDataStore.data.first().id)
        if (networkUser.isSuccessful && networkUser.body() != null)
            if (networkUserToUser(networkUser.body()!!) != userDataStore.data.first())
                userDataStore.updateData {
                    networkUserToUser(networkUser.body()!!)
                }

        return userDataStore.data
    }

    override suspend fun updateCurrent(user : User) {
        userDataStore.updateData {
            user
        }


        val networkUser = userToNetworkUser(user)
        val savedBooks = JsonObject()
        val gson = Gson()

        FolderRepository.getAllFolders().forEach {
            savedBooks.addProperty(it.folderName, gson.toJson(it.bookIds))
        }
        networkUser.savedBooks = savedBooks

        Logger.getGlobal().info(networkUser.toString())
        remoteApi.updateUser(networkUser.id, networkUser)
    }

    override suspend fun updateCurrent() {
        updateCurrent(userDataStore.data.first())
    }

    override suspend fun getOther(userId : Int) : User {
        val userData = remoteApi.getUser(userId)
        if (userData.isSuccessful)
            return networkUserToUser(userData.body()!!)
        else
            throw HttpException(userData)
    }

    override suspend fun register(user : User) : String {
        return remoteApi.createUser(userToNetworkUser(user)).body() ?: "No response"
    }

    override suspend fun login(email : String, password : String) : String {
        return remoteApi.login(UserLogin(email = email, password = password)).body()
            ?: "No response"
    }

    override suspend fun delete(userId : Int) : String {
        return remoteApi.deleteUser(userDataStore.data.first().id).body() ?: "No response"
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
