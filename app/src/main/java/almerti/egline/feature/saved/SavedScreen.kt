package almerti.egline.feature.saved

import androidx.compose.runtime.Composable

@Composable
fun SavedScreen(
    viewModel : SavedViewModel
) {
    val state = viewModel.state
    //
    // for (book in state.books!!) {
    //     BookCard(book = book) {
    //
    //     }
    // }
}
