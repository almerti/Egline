package almerti.egline.feature.settings

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val SETTINGS_ROUTE = "settings"

internal fun NavGraphBuilder.SettingsScreen() {
    composable(SETTINGS_ROUTE) {
        val viewModel : SettingsViewModel = hiltViewModel<SettingsViewModel>()
        SettingsScreen(viewModel)
    }
}
