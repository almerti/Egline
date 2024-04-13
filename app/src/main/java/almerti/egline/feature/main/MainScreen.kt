package almerti.egline.feature.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    state: MainState,
    actions: MainActions,
) {
  // TODO UI Rendering
    Box(modifier = Modifier.padding(16.dp)) {
        Text(text = "Main Screen")
    }
}

@Composable
@Preview(name = "Main")
private fun MainScreenPreview() {
    MainScreen(
        state = MainState(),
        actions = MainActions()
    )
}

