package 

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(
    state: MainState,
    actions: MainActions,
) {
  // TODO UI Rendering
}

@Composable
@Preview(name = "Main")
private fun MainScreenPreview() {
    MainScreen(
        state = MainState(),
        actions = MainActions()
    )
}

