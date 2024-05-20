package almerti.egline.feature.saved

import almerti.egline.feature.favorite.components.FavoriteBooks
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SavedScreen(
    viewModel : SavedViewModel ,
    onNavigateToBookPage : (Int) -> Unit
) {
    val state = viewModel.state
    Scaffold { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 16.dp ,
                    top = it.calculateTopPadding() + 16.dp ,
                    end = 16.dp ,
                    bottom = it.calculateBottomPadding() + 100.dp
                )
        ) {
            FavoriteBooks(
                books = state.bookItems ,
                navigateToBookPage = onNavigateToBookPage
            )
        }
    }
}
