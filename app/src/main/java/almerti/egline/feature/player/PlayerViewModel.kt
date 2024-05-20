package almerti.egline.feature.player

import almerti.egline.data.repository.BookRepository
import almerti.egline.data.repository.ChapterRepository
import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class PlayerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val chapterRepository: ChapterRepository,
    private val bookRepository: BookRepository,
) : ViewModel() {
    private val args = PlayerChapterArg(savedStateHandle)
    var mediaItem by mutableStateOf<MediaItem?>(null)
    var cover by mutableStateOf(byteArrayOf())

    var bookTitle by mutableStateOf("")
    var chapterTitle by mutableStateOf("")

    init {
        viewModelScope.launch {
            val chapter = chapterRepository.get(args.chapterId)
            val book = bookRepository.getById(chapter.bookId)

            bookTitle = book?.title!!
            chapterTitle = chapter.title
            cover = book.cover

            val uri = chapterRepository.getAudio(chapter.id).toUri()
            mediaItem = MediaItem.fromUri(uri)
        }
    }
}
