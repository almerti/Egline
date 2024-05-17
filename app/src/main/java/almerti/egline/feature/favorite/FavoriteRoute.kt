package almerti.egline.feature.favorite

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val FAVORITE_GRAPH_ROUTE = "favorite_route"

fun NavController.navigateToFavoriteGraph() {
    navigate(FAVORITE_GRAPH_ROUTE)
}

fun NavGraphBuilder.favoriteGraph() {
    navigation(
        route = FAVORITE_GRAPH_ROUTE,
        startDestination = FAVORITE_ROUTE,
    ) {
        favoriteScreen()
    }
}

