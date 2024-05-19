package almerti.egline.feature.profile.main

import almerti.egline.data.model.Book
import almerti.egline.data.model.User
import almerti.egline.data.repository.BookRepository
import almerti.egline.data.repository.FolderRepository
import almerti.egline.data.repository.UserRepository
import almerti.egline.feature.favorite.BookItem
import almerti.egline.feature.favorite.FavoriteState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    private val userRepository: UserRepository,
    private val folderRepository: FolderRepository,
    private val bookRepository: BookRepository,
) : ViewModel() {
    var state by mutableStateOf(FavoriteState())
    private lateinit var userFlow: Flow<User>
    private val idsList = mutableListOf<Int>()

    private val _userState = MutableStateFlow<User?>(null)
    val userState: StateFlow<User?> = _userState

    init {
        getUser()
        getBooksIds()
        getLastAddedBooks()
    }

    private fun getUser() {
        viewModelScope.launch {
            userFlow = userRepository.get()
            userFlow.collect {_userState.value = it}
        }
    }

    private fun getBooksIds() {
        viewModelScope.launch {
            val foldersFlow = folderRepository.getAll()

            foldersFlow.collect {folders ->
                if (folders.isNotEmpty()) {
                    folders.forEach {folder ->
                        if (folder.bookIds.isNotEmpty()) {
                            idsList.add(folder.bookIds.last())
                        }
                    }
                }

                state = state.copy(
                    folders = folders,
                )
            }
        }
    }

    private fun getLastAddedBooks() {
        viewModelScope.launch {
            val localBooksFlow = bookRepository.getSaved()

            localBooksFlow.collect {localBooks ->
                val requiredBooks = mutableListOf<Book?>()

                idsList.forEach {id ->
                    if (localBooks.any {book -> book.id == id}) {
                        if (!requiredBooks.contains(requiredBooks.find {it?.id!! == id})) {
                            requiredBooks.add(localBooks.find {it.id == id})
                        }
                    } else {
                        requiredBooks.add(bookRepository.getById(id))
                    }
                }

                state = state.copy(
                    currentFolder = null,
                    bookList = bookToBookItem(requiredBooks),
                )
            }
        }
    }

    private fun bookToBookItem(
        books: List<Book?>
    ): List<BookItem> {
        val bookItems = mutableListOf<BookItem>()
        books.forEach {book ->
            if (book != null) {
                bookItems.add(
                    BookItem(
                        bookId = book.id,
                        bookTitle = book.title,
                        bookCover = book.cover,
                    ),
                )
            }
        }
        return bookItems
    }

    fun onLogout(): () -> Unit = {
        viewModelScope.launch {
            _userState.value = null
            userRepository.logout()
        }
    }
}
