package almerti.egline.feature.login

import almerti.egline.data.repository.UserRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _loginResultState = MutableStateFlow("")
    val loginResultState: StateFlow<String> = _loginResultState;

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginResultState.value = userRepository.login(email, password)
        }
    }
}
