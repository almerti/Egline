package almerti.egline.feature.favorite

import almerti.egline.data.model.Book
import almerti.egline.data.repository.BookRepository
import almerti.egline.data.repository.FolderRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val folderRepository : FolderRepository,
    private val bookRepository : BookRepository
) : ViewModel() {
    var state by mutableStateOf(FavoriteState())

    init {
        getFolders()
    }

    private fun getFolders() {
        viewModelScope.launch {
            val flow = folderRepository.getAll()
            var bookList : List<Book?> = emptyList()

            flow.collect {it ->
                if (it.isNotEmpty() && it[0].bookIds.isNotEmpty()) {
                    bookList = it[0].bookIds.map {
                        bookRepository.getById(it)
                    }
                }

                state = state.copy(
                    folders = it,
                    currentFolder = if (it.isEmpty()) null else it[0],
                    bookList = getBooks(bookList),
                )
            }
        }
    }

    private fun getBooks(
        books : List<Book?>
    ) : List<BookItem> {
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

    fun onEvent(event : FavoriteEvent) {
        when (event) {
            is FavoriteEvent.AddFolder -> {

            }

            is FavoriteEvent.ChangeCurrentFolder -> {
                viewModelScope.launch {
                    var bookList : List<Book?> = emptyList()

                    if (event.folder.bookIds.isNotEmpty()) {
                        bookList = event.folder.bookIds.map {
                            bookRepository.getById(it)
                        }
                    }

                    state = state.copy(
                        currentFolder = event.folder,
                        bookList = getBooks(bookList),
                    )
                }
            }

            is FavoriteEvent.ChangeBookInFolder -> TODO()
            is FavoriteEvent.FindBookInFolder -> TODO()
            is FavoriteEvent.RemoveBookFromFolder -> TODO()
            is FavoriteEvent.RemoveFolder -> TODO()
        }
    }
}
