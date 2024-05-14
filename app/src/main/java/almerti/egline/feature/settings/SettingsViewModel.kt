package almerti.egline.feature.settings

import almerti.egline.data.model.User
import almerti.egline.data.repository.UserRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository : UserRepository
) : ViewModel() {
    private lateinit var userFlow : Flow<User>

    private val _userState = MutableStateFlow<User?>(null)
    val userState : StateFlow<User?> = _userState

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            userFlow = repository.getCurrentUser()
            userFlow.collect {user ->
                _userState.value = user
                Logger.getGlobal().info(user.toString())
            }
        }
    }

    fun updateUser(name : String) {
        viewModelScope.launch {
            val user = User(
                id = 1,
                displayName = name,
                email = "test",
                avatar = "get".toByteArray(),
                password = "NewPassword",
            )
            repository.updateCurrentUser(user)
        }
    }
}
