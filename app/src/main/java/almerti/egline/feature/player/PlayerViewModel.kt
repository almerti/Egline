package almerti.egline.feature.player

import almerti.egline.data.repository.BookRepository
import almerti.egline.data.repository.ChapterRepository
import android.annotation.SuppressLint
import android.util.Log
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
    private val savedStateHandle: SavedStateHandle,
    private val chapterRepository: ChapterRepository,
    private val bookRepository: BookRepository,
) : ViewModel() {
    private val args = PlayerChapterArg(savedStateHandle)
    var mediaItems by mutableStateOf(mutableListOf<MediaItem>())
    val chaptersTitles by mutableStateOf(mutableListOf<String>())
    var cover by mutableStateOf(byteArrayOf())
    var bookTitle: String = ""
    var currentChapter: Int = 0

    init {
        viewModelScope.launch {
            val chapter = chapterRepository.get(args.chapterId)
            Log.e("FOREIGN KEY", "KEY: " + chapter.bookId)
            val book = bookRepository.getById(chapter.bookId)

            bookTitle = book?.title!!
            cover = book.cover

            val allChapters = chapterRepository.getAll(book.id).sortedBy {it.number}
            allChapters.forEachIndexed {index, chapter ->
                val uri = chapterRepository.getAudio(chapter.id).toUri()
                mediaItems.add(MediaItem.fromUri(uri))
                chaptersTitles.add("Chapter #${chapter.number} - ${chapter.title}")

                if (chapter.id == args.chapterId) {
                    currentChapter = index
                }
            }
        }
    }
}
