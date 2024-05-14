package almerti.egline.data.repository

import almerti.egline.data.model.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    suspend fun getCurrentUser() : Flow<User>
    suspend fun updateCurrentUser(user : User)
    suspend fun getOtherUser(userId : Int) : User

    suspend fun CreateUser(user : User)
    suspend fun DeleteUser(userId : Int)
}
