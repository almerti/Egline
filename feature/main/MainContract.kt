package 

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


/**
 * UI State that represents MainScreen
**/
class MainState

/**
 * Main Actions emitted from the UI Layer
 * passed to the coordinator to handle
**/
data class MainActions(
    val onClick: () -> Unit = {}
)