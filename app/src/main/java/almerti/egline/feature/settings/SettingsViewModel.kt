package almerti.egline.feature.settings

import almerti.egline.data.network.RetrofitEglineNetworkApi
import almerti.egline.data.network.model.Book
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    val retrofitEglineNetworkApi : RetrofitEglineNetworkApi
) : ViewModel() {
    val bookState = MutableStateFlow<List<Book>>(emptyList())
    init {
        GetBooks()
    }
    private fun GetBooks()
    {
        viewModelScope.launch {
            val response = retrofitEglineNetworkApi.GetBooks()
            if(response.isSuccessful)
            {
                bookState.value= response.body()!!
            }
            else
            {
                Logger.getLogger("SettingsViewModel").warning("Error")
            }

        }
    }
}