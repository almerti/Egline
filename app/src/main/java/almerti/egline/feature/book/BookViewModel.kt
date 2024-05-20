package almerti.egline.feature.book

import almerti.egline.data.model.Comment
import almerti.egline.data.repository.BookRepository
import almerti.egline.data.repository.ChapterRepository
import almerti.egline.data.repository.CommentRepository
import almerti.egline.data.repository.UserRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    savedStateHandle : SavedStateHandle,
    private val bookRepository : BookRepository,
    private val chapterRepository : ChapterRepository,
    private val commentRepository : CommentRepository,
    private val userRepository : UserRepository
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
            is BookEvent.AddComment -> {
                viewModelScope.launch {
                    commentRepository.add(
                        Comment(
                            bookId = args.bookId,
                            text = event.comment,
                            userId = userRepository.get().first().id,
                            rating = 5,
                        ),
                    )
                    state.comments = commentRepository.getAll(args.bookId)
                }
            }

            is BookEvent.DeleteComment -> TODO()
            is BookEvent.UpdateRate -> TODO()
            is BookEvent.AddRate -> TODO()
            is BookEvent.DownloadAllChapters -> {
                viewModelScope.launch {
                    chapterRepository.saveToDb(state.chapters)
                }
            }

            is BookEvent.DownloadChapter -> {
                viewModelScope.launch {
                    chapterRepository.saveToDb(event.chapterId)
                }
            }
        }
    }
}
