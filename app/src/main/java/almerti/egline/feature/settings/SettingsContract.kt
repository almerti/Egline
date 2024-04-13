package almerti.egline.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents SettingsScreen
 **/
class SettingsState

/**
 * Settings Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class SettingsActions(
    val onClick: () -> Unit = {}
)