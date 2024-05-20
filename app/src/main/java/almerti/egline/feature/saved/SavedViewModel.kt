package almerti.egline.feature.saved

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
class SavedViewModel @Inject constructor(
    private val bookRepository : BookRepository
) : ViewModel() {
    var state by mutableStateOf(SavedState())


    init {
        loadState()
    }

    private fun loadState() {
        viewModelScope.launch {

            bookRepository.getSaved().collect { books ->
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
}
