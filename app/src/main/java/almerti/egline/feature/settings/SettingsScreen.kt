package almerti.egline.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun SettingsScreen(
    viewModel : SettingsViewModel
) {
    val user by viewModel.userState.collectAsState(initial = null)
    var newDisplayName by remember {mutableStateOf("")}
    var folder by remember {mutableStateOf("")}

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

        Button(onClick = {viewModel.updateUser(newDisplayName)}) {
            Text(text = "Update User")
        }

        TextField(
            value = folder,
            onValueChange = {folder = it},
            label = {Text("folder name")},
        )

        Button(onClick = {viewModel.addToFolder(folder)}) {
            Text(text = "add to folder")
        }
        Button(onClick = {viewModel.logintest()}) {
            Text(text = "login")
        }
    }
}
