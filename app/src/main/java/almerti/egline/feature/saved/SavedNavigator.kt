package almerti.egline.feature.saved

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val SAVED_ROUTE = "saved"

internal fun NavGraphBuilder.savedScreen() {
    composable(SAVED_ROUTE) {
        val viewModel : SavedViewModel = hiltViewModel<SavedViewModel>()
        SavedScreen(
            viewModel = viewModel,
        )
    }
}
