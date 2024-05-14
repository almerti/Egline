package almerti.egline.feature.settings

import almerti.egline.data.model.User
import almerti.egline.data.repository.UserRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    val repository : UserRepository
) : ViewModel() {
    var userState = MutableStateFlow<User?>(null)

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            repository.getCurrentUser().collect {user ->
                userState.value = user
            }
        }
    }

    fun updateUser() {
        viewModelScope.launch {
            val user = User(
                id = userState.value?.id ?: 0,
                displayName = "New Name",
                email = "New Email",
                avatar = "New Avatar".toByteArray(),
                password = "New Password",
            )
            repository.updateCurrentUser(user)
        }
    }
}
