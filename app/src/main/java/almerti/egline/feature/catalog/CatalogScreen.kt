package almerti.egline.feature.catalog

import almerti.egline.feature.favorite.components.FavoriteBooks
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CatalogScreen(
    viewModel : CatalogViewModel ,
    onNavigateToBook : (Int) -> Unit
) {
    val state = viewModel.state

    if (state.books != null)
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
                    books = state.bookItems!! ,
                    navigateToBookPage = onNavigateToBook
                )
            }
        }
    else
        Column(
            modifier = Modifier.fillMaxSize() ,
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
}
