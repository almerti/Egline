package almerti.egline.feature.book

import almerti.egline.data.repository.BookRepository
import almerti.egline.data.repository.ChapterRepository
import almerti.egline.data.repository.CommentRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    savedStateHandle : SavedStateHandle,
    private val bookRepository : BookRepository,
    private val chapterRepository : ChapterRepository,
    private val commentRepository : CommentRepository
) : ViewModel() {
    private val args = BookCardArgs(savedStateHandle)

    var state by mutableStateOf(BookState())

    init {
        getBook()
    }

    private fun getBook() {
        viewModelScope.launch {
            state.book = bookRepository.getById(args.bookId)
            state.chapters = chapterRepository.getAll(args.bookId)
            state.comments = commentRepository.getAll(args.bookId)
        }
    }


    fun onEvent(event : BookEvent) {
        when (event) {
            is BookEvent.RateComment -> TODO()
            is BookEvent.UpdateComment -> TODO()
            is BookEvent.AddComment -> TODO()
            is BookEvent.DeleteComment -> TODO()
            is BookEvent.UpdateRate -> TODO()
            is BookEvent.AddRate -> TODO()
        }
    }
}
