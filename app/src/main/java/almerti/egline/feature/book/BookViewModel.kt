package almerti.egline.feature.book

import almerti.egline.data.repository.BookRepository
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class BookViewModel @Inject constructor(
    private val savedStateHandle : SavedStateHandle ,
    private val bookRepository : BookRepository ,
) : ViewModel() {
    private val args = BookCardArgs(savedStateHandle)

    var state = BookState()

    init {
        getBook()
    }

    fun getBook() {
        viewModelScope.launch {
            state = BookState(bookRepository.getById(args.bookId))
        }
    }
}
