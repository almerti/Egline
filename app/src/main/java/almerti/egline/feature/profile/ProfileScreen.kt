package almerti.egline.feature.profile

import android.annotation.SuppressLint
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onNavigateToLoginPage: () -> Unit
) {
    val user = viewModel.userState

    if (user.value?.id == -1 || user.value == null)
        Button(onClick = onNavigateToLoginPage) {
            Text(text = "Login")
        }
    else
        Text(text = "Fuck")
}
