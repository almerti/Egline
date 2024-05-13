package almerti.egline.feature.settings

import almerti.egline.data.network.model.Book
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel
) {
    val books by viewModel.bookState.collectAsState()

    Column {
        Text(text = "SettingsScreen")

        if (books.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(books) { book ->
                    BookItem(book)
                }
            }
        }
        Button(onClick = {
            viewModel.viewModelScope.launch {
            viewModel.addRating()
            }
        }
        ) {
            Text(text = "Add Rating")
        }
        }
    }
@Composable
fun BookItem(book: Book) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(book.title)
        Spacer(modifier = Modifier.width(8.dp))
        Text(book.description)
        Spacer(modifier = Modifier.width(10.dp))
        Text(book.rating.toString())
    }
}