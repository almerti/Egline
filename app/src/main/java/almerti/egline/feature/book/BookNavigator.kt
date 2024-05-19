package almerti.egline.feature.book

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val BOOK_ROUTE = "book"

internal fun NavGraphBuilder.bookScreen() {
    composable(BOOK_ROUTE) {
        val viewModel : BookViewModel = hiltViewModel<BookViewModel>()
        BookScreen(
            viewModel = viewModel
        )
    }
}

