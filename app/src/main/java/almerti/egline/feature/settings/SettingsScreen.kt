package almerti.egline.feature.settings

import almerti.egline.data.network.model.Book
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel
) {
    val books by settingsViewModel.bookState.collectAsState()

    Column {
        Text(text = "SettingsScreen")

        if (books.isEmpty()) {
            // Отображаем индикатор загрузки или сообщение, когда список книг пуст
            CircularProgressIndicator()
        } else {
            // Отображаем список книг
            LazyColumn {
                items(books) { book ->
                    BookItem(book)
                }
            }
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
    }
}