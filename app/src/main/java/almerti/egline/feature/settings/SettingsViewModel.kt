package almerti.egline.feature.settings

import almerti.egline.data.model.Book
import almerti.egline.data.model.Folder
import almerti.egline.data.model.User
import almerti.egline.data.repository.BookRepository
import almerti.egline.data.repository.FolderRepository
import almerti.egline.data.repository.UserRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userRepository : UserRepository,
    private val bookRepository : BookRepository

) : ViewModel() {
    private lateinit var userFlow : Flow<User>

    private val _userState = MutableStateFlow<User?>(null)
    val userState : StateFlow<User?> = _userState

    val book = MutableStateFlow<Book?>(null)

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            userFlow = userRepository.get()
            userFlow.collect {_userState.value = it}
        }
    }

    suspend fun getBook() {
        viewModelScope.launch {
            book.value = bookRepository.getById(1)
        }
    }

}
