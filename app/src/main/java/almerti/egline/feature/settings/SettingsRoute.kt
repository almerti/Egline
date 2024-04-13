package almerti.egline.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun SettingsRoute(
    coordinator: SettingsCoordinator = rememberSettingsCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(SettingsState())

    // UI Actions
    val actions = rememberSettingsActions(coordinator)

    // UI Rendering
    SettingsScreen(uiState, actions)
}


@Composable
fun rememberSettingsActions(coordinator: SettingsCoordinator): SettingsActions {
    return remember(coordinator) {
        SettingsActions(
            onClick = coordinator::doStuff
        )
    }
}