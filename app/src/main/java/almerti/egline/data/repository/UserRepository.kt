package almerti.egline.data.repository

import almerti.egline.data.model.User

interface UserRepository {
    suspend fun getCurrentUser() : User
    suspend fun updateCurrentUser(user : User)
    suspend fun getOtherUser(userId : Int) : User
}
