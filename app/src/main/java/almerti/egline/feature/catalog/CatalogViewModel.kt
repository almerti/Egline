package almerti.egline.feature.catalog

import almerti.egline.data.repository.BookRepository
import almerti.egline.feature.favorite.BookItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val bookRepository : BookRepository
) : ViewModel() {
    var state by mutableStateOf(CatalogState())

    init {
        loadState()
        getBook()
    }

    private fun loadState() {
        viewModelScope.launch {
            bookRepository.getFlow().collect { books ->
                state = state.copy(books = books)
                state = state.copy(bookItems = books.map { book ->
                    BookItem(
                        book.id ,
                        book.title ,
                        book.cover
                    )
                })
            }
        }
    }

    private fun getBook() {
        viewModelScope.launch {
            bookRepository.getNext(2)
        }
    }
}
