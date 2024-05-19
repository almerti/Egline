package almerti.egline.feature.favorite

import almerti.egline.R
import almerti.egline.feature.favorite.components.FavoriteBooks
import almerti.egline.feature.favorite.components.FavoriteFoldersSlider
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel
) {
    val state = viewModel.state
    Scaffold {paddingValues ->
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = stringResource(id = R.string.favorite_header),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
            ),
        )
        if (state.folders == null)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CircularProgressIndicator()
            }
        else
            if (state.currentFolder == null)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(text = stringResource(id = R.string.favorite_no_folders_message))
                }
            else
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = paddingValues.calculateTopPadding() + 24.dp,
                            start = 16.dp,
                            end = 16.dp,
                            bottom = paddingValues.calculateBottomPadding() + 16.dp,
                        ),
                ) {
                    FavoriteFoldersSlider(
                        folders = state.folders,
                        current = state.currentFolder,
                        changeCurrentFolder = {
                            viewModel.onEvent(FavoriteEvent.ChangeCurrentFolder(it))
                        },
                    )
                    if (state.currentFolder.bookIds.size > 0) {
                        FavoriteBooks(books = state.bookList!!, navigateToBookPage = {})
                    } else {
                        Text(
                            text = stringResource(id = R.string.favorite_no_books_message),
                        )
                    }
                }
    }
}
