package almerti.egline.feature.settings

import almerti.egline.data.source.network.model.Book
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
    viewModel : SettingsViewModel
) {
    val user by viewModel.userState.collectAsState()

    Column {
        Text(text = "SettingsScreen")

        if (user == null) {
            CircularProgressIndicator()
        } else {
            Column {
                Text(text = user?.displayName ?: "")
                Text(text = user?.email ?: "")
            }
        }

        Button(onClick = {viewModel.updateUser()}) {
            Text(text = "Update User")
        }
    }
}
