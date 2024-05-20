package almerti.egline.feature.catalog

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation


const val CATALOG_GRAPH_ROUTE = "catalog_route"

fun NavController.navigateToCatalogGraph() {
    navigate(CATALOG_GRAPH_ROUTE)
}

fun NavGraphBuilder.catalogGraph() {
    navigation(
        route = CATALOG_GRAPH_ROUTE,
        startDestination = CATALOG_ROUTE,
    ) {
        catalogScreen()
    }
}
