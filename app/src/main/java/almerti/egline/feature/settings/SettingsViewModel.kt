package almerti.egline.feature.settings

import almerti.egline.data.model.Folder
import almerti.egline.data.model.User
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
    private val FolderRepository : FolderRepository

) : ViewModel() {
    private lateinit var userFlow : Flow<User>

    private val _userState = MutableStateFlow<User?>(null)
    val userState : StateFlow<User?> = _userState

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            userFlow = userRepository.get()
            userFlow.collect {_userState.value = it}
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
            userRepository.update(user)
        }
    }

    fun addToFolder(name : String) {
        viewModelScope.launch {
            FolderRepository.addBooks(
                Folder(
                    folderName = name,
                    bookIds = mutableListOf(2, 5, 3),
                ),
            )
        }
    }

    fun getFolders() {
        viewModelScope.launch {
            val ele = FolderRepository.getAll()
            ele.forEach(
                fun(it : Folder) {
                    Logger.getGlobal().info(it.toString())
                },
            )
        }
    }

    fun logintest() {
        viewModelScope.launch {
            val res = userRepository.login("test@i.o", "1122")
            Logger.getGlobal().info(res.toString())
        }
    }
}
