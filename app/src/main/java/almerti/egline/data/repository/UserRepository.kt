package almerti.egline.data.repository

import almerti.egline.data.model.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    suspend fun getCurrent() : Flow<User>
    suspend fun updateCurrent(user : User)
    suspend fun updateCurrent()

    suspend fun getOther(userId : Int) : User

    suspend fun register(user : User) : String

    suspend fun login(email : String, password : String) : String
    suspend fun delete(userId : Int) : String
}
