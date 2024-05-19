package almerti.egline.feature.book

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
internal fun BookScreen(
    viewModel : BookViewModel
) {
    val state = viewModel.state
    state.book?.let {Text(text = it.title)}

}
