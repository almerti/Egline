package almerti.egline.feature.saved

import almerti.egline.data.repository.BookRepository
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val bookRepository : BookRepository
) : ViewModel() {
    var state = SavedState()


    init {

    }

    private suspend fun loadState() {
        bookRepository.getSaved().collect {
            state = state.copy(
                books = it,
            )
        }
    }
}
