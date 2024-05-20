package almerti.egline.feature.saved

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val SAVED_GRAPH_ROUTE = "saved_route"

fun NavController.navigateToSavedGraph() {
    navigate(SAVED_GRAPH_ROUTE)
}

fun NavGraphBuilder.savedGraph() {
    navigation(
        route = SAVED_GRAPH_ROUTE,
        startDestination = SAVED_ROUTE,
    ) {
        savedScreen()
    }
}

