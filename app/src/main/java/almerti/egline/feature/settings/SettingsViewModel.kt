package almerti.egline.feature.settings

import almerti.egline.data.source.network.NetworkApi
import almerti.egline.data.source.network.model.Book
import almerti.egline.data.source.network.model.BookRate
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    val networkApi : NetworkApi
) : ViewModel() {
    val bookState = MutableStateFlow<List<Book>>(emptyList())

    init {
        GetBooks()
    }

    private fun GetBooks() {
        viewModelScope.launch {
            val response = networkApi.getBooks()
            if (response.isSuccessful) {
                bookState.value = response.body()!!
            } else {
                Logger.getLogger("SettingsViewModel").warning("Error")
            }

        }
    }

    suspend fun addRating() {
        val bookRate = BookRate(
            bookId = 1,
            userId = 1,
            rate = 2,
        )
        networkApi.addRateToBook(bookRate)
    }
}
