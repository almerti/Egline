package almerti.egline.feature.catalog

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val CATALOG_ROUTE = "catalog"

internal fun NavGraphBuilder.catalogScreen() {
    composable(CATALOG_ROUTE) {
        val viewModel : CatalogViewModel = hiltViewModel<CatalogViewModel>()
        CatalogScreen(
            viewModel = viewModel,
        )
    }
}
