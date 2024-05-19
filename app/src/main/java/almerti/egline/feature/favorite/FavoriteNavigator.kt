package almerti.egline.feature.favorite

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val FAVORITE_ROUTE = "favorite"

internal fun NavGraphBuilder.favoriteScreen() {
    composable(FAVORITE_ROUTE) {
        val viewModel: FavoriteViewModel = hiltViewModel<FavoriteViewModel>()
        FavoriteScreen(
            viewModel = viewModel,
        )
    }
}
