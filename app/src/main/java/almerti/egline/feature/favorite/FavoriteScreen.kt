package almerti.egline.feature.favorite

import almerti.egline.R
import almerti.egline.feature.favorite.components.FavoriteBooks
import almerti.egline.feature.favorite.components.FavoriteBottomSheet
import almerti.egline.feature.favorite.components.FavoriteFoldersSlider
import almerti.egline.feature.favorite.components.FavoriteTopAppBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen(
    viewModel : FavoriteViewModel
) {
    val state = viewModel.state
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember {mutableStateOf(false)}
    Scaffold(
        topBar = {
            FavoriteTopAppBar(
                onClick = {
                    showBottomSheet = true
                },
            )
        },

        ) {paddingValues ->

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
                    Text(
                        text = stringResource(id = R.string.favorite_no_folders_message),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                        ),
                    )
                }
            else
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = paddingValues.calculateTopPadding(),
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

        if (showBottomSheet) {
            FavoriteBottomSheet(
                Modifier,
                sheetState,
                onDismissRequest = {
                    showBottomSheet = false
                },
                state,
                onEditFolder = {oldName, newName ->
                    viewModel.onEvent(FavoriteEvent.ChangeFolderName(oldName, newName))
                },
                onAddNewFolder = {
                    viewModel.onEvent(FavoriteEvent.AddFolder(it))
                },
                onDeleteFolder = {
                    viewModel.onEvent(FavoriteEvent.RemoveFolder(it))
                },
            )
        }
    }
}
