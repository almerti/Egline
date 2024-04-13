package almerti.egline.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingsScreen(
    state: SettingsState,
    actions: SettingsActions,
) {
    // TODO UI Rendering
}

@Composable
@Preview(name = "Settings")
private fun SettingsScreenPreview() {
    SettingsScreen(
        state = SettingsState(),
        actions = SettingsActions()
    )
}

