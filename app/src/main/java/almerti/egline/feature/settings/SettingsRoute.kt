package almerti.egline.feature.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val SETTINGS_GRAPH_ROUTE = "settings_route"


fun NavController.navigateToSettingsGraph() {
    navigate(SETTINGS_GRAPH_ROUTE)
}

fun NavGraphBuilder.settingsGraph() {
    navigation(
        route = SETTINGS_GRAPH_ROUTE,
        startDestination = SETTINGS_ROUTE
    ) {
        SettingsScreen()
    }
}

