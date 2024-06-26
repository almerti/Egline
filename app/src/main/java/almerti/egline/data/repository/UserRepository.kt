package almerti.egline.data.repository

import almerti.egline.data.model.User
import almerti.egline.data.source.network.model.UserEdit
import kotlinx.coroutines.flow.Flow


interface UserRepository {
    suspend fun get(): Flow<User>
    suspend fun get(userId: Int): User?
    suspend fun update(user: User)
    suspend fun delete(userId: Int): String
    suspend fun sendDataToServer()
    suspend fun fetchDataFromServer()
    suspend fun register(user: User): String
    suspend fun login(email: String, password: String): String
    suspend fun edit(id: Int, userEdit: UserEdit): String
    suspend fun logout()
}
