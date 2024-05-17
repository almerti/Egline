package almerti.egline.feature.favorite

import almerti.egline.feature.favorite.components.FavoriteBooks
import almerti.egline.feature.favorite.components.FavoriteFoldersSlider
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel
) {
    val state = viewModel.state
    if (state.folders.isEmpty())
        Scaffold {paddingValues ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = "Favorites",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                ),
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(text = "No folders?")
            }
        }
    else
        Scaffold {paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = paddingValues.calculateTopPadding() + 24.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = paddingValues.calculateBottomPadding() + 16.dp,
                    )
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    text = "Favorites",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                )
                state.currentFolder?.let {it ->
                    FavoriteFoldersSlider(
                        folders = state.folders,
                        current = it,
                        changeCurrentFolder = {
                            viewModel.onEvent(FavoriteEvent.ChangeCurrentFolder(it))
                        },
                    )
                }
                if (state.currentFolder != null && state.currentFolder.bookIds.size > 0) {
                    FavoriteBooks(books = state.bookList, navigateToBookPage = {})
                } else {
                    Text(
                        text = ":(",
                    )
                }
            }
        }
}
