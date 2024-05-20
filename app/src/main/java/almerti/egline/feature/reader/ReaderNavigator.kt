package almerti.egline.feature.reader

import almerti.egline.feature.player.navigateToPlayerGraph
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val READER_ROUTE = "reader"

internal fun NavGraphBuilder.readerScreen(
    onBack: () -> Unit,
    navController: NavController
) {
    composable(READER_ROUTE) {
        val viewModel: ReaderViewModel = hiltViewModel<ReaderViewModel>()
        ReaderScreen(
            viewModel = viewModel,
            onBack = onBack,
            onNavigateToAudio = {
                navController.navigateToPlayerGraph(it)
            },
        )
    }
}
