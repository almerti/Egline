package almerti.egline.feature.reader

import almerti.egline.data.repository.ChapterRepository
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
class ReaderViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val chapterRepository: ChapterRepository
) : ViewModel() {
    private val args = ReaderChapterArg(savedStateHandle)
    var text by mutableStateOf<String?>(null)
    var title by mutableStateOf<String?>(null)
    var fontSize by mutableStateOf(18)
    var chapterId by mutableStateOf(0)

    init {
        viewModelScope.launch {
            val chapter = chapterRepository.get(args.chapterId)
            text = chapter.textContent
            title = chapter.title
            chapterId = chapter.id
        }
    }

    fun changeFontSize(newFontSize: Int) {
        fontSize = newFontSize
    }
}
