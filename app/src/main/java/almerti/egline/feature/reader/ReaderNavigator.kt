package almerti.egline.feature.reader

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val READER_ROUTE = "reader"

internal fun NavGraphBuilder.readerScreen(
    onBack: () -> Unit
) {
    composable(READER_ROUTE) {
        val viewModel: ReaderViewModel = hiltViewModel<ReaderViewModel>()
        ReaderScreen(
            viewModel = viewModel,
            onBack = onBack,
        )
    }
}
