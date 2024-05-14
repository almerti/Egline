package almerti.egline.data.repository

import almerti.egline.data.model.User
import almerti.egline.data.source.network.NetworkApi
import android.content.SharedPreferences
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteApi : NetworkApi,
    private val sharedPreferences : SharedPreferences
) : UserRepository {
    override suspend fun getCurrentUser() : User {
        TODO("Not yet implemented")
    }

    override suspend fun updateCurrentUser(user : User) {
        TODO("Not yet implemented")
    }

    override suspend fun getOtherUser(userId : Int) : User {
        TODO("Not yet implemented")
    }
}
