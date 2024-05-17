package almerti.egline.data.repository

import almerti.egline.data.model.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    suspend fun get() : Flow<User>
    suspend fun get(userId : Int) : User
    suspend fun update(user : User)
    suspend fun update()
    suspend fun delete(userId : Int) : String
    suspend fun register(user : User) : String
    suspend fun login(email : String , password : String) : String
}
