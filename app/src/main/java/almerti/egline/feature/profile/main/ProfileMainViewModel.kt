package almerti.egline.feature.profile.main

import almerti.egline.data.model.User
import almerti.egline.data.repository.UserRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileMainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private lateinit var userFlow: Flow<User>

    private val _userState = MutableStateFlow<User?>(null)
    val userState: StateFlow<User?> = _userState

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            userFlow = userRepository.get()
            userFlow.collect {_userState.value = it}
        }
    }

    fun onLogout(): () -> Unit = {
        viewModelScope.launch {
            _userState.value = null
            userRepository.logout()
        }
    }
}
