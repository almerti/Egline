package almerti.egline.feature.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@Composable
fun settingsScreen(
    viewModel : SettingsViewModel
) {
    val user by viewModel.userState.collectAsState(initial = null)
    var newDisplayName by remember {mutableStateOf("")}

    Box(
        modifier = Modifier
            .background(Color.Blue)
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {


        Column {
            Text(text = "SettingsScreen")

            if (user == null) {
                CircularProgressIndicator()
            } else {
                Column {
                    TextField(
                        value = newDisplayName,
                        onValueChange = {newDisplayName = it},
                        label = {Text("New Display Name")},
                    )
                    Text(text = user?.displayName ?: "")
                    Text(text = user?.email ?: "")
                }
            }

            val coroutineScope = rememberCoroutineScope()
            val getBook : () -> Unit = {
                coroutineScope.launch {
                    viewModel.getBook()
                }
            }
            Text(text = viewModel.book.value?.title ?: "")

            Button(
                onClick = {
                    getBook()
                },
            ) {
                Text(text = "book")
            }
        }
    }
}
