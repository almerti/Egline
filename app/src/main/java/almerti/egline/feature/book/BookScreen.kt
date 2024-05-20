package almerti.egline.feature.book

import almerti.egline.feature.book.components.TabBar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CreateNewFolder
import androidx.compose.material.icons.filled.NoPhotography
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SaveAlt
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BookScreen(
    viewModel : BookViewModel,
    onBack : () -> Unit,
    onOpenBookReader : (Int) -> Unit,
) {
    val state = viewModel.state
    var folderName by remember {mutableStateOf("")}

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = state.book?.title.orEmpty())
                },
                navigationIcon = {
                    IconButton(onClick = {onBack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {viewModel.onEvent(BookEvent.DownloadAllChapters(state.book!!.id))}) {
                        Icon(
                            imageVector = Icons.Filled.SaveAlt,
                            contentDescription = "More",
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {onOpenBookReader(state.chapters[0].id)},
                icon = {Icon(Icons.Filled.PlayArrow, "Extended floating action button.")},
                text = {Text(text = "Start")},
            )
        },
    ) {paddingValues ->
        if (state.book == null) {
            CircularProgressIndicator(
                modifier = Modifier
                    .safeContentPadding()
                    .padding(top = 100.dp)
                    .width(200.dp),
            )
            viewModel.getBook()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    SubcomposeAsyncImage(

                        model = if (state.book?.cover?.isEmpty() == true)
                            Icon(
                                imageVector = Icons.Filled.NoPhotography,
                                contentDescription = null,
                                modifier = Modifier
                                    .height(200.dp)
                                    .size(150.dp)
                                    .background(color = MaterialTheme.colorScheme.surfaceContainer),
                            )
                        else state.book?.cover,
                        contentDescription = null,
                        modifier = Modifier
                            .width(160.dp)
                            .padding(end = 16.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start,
                    ) {
                        state.book?.let {
                            Text(
                                text = it.title,
                                style = MaterialTheme.typography.titleLarge,
                                fontSize = 30.sp,
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .height(20.dp),
                        )
                        // Rating
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${state.book?.rating ?: 0.0}",
                                style = MaterialTheme.typography.bodyMedium,
                                fontSize = 20.sp,
                            )
                        }

                        // Year
                        Text(
                            text = "Date: ${state.book?.year}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 20.sp,
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        // Views
                        Text(
                            text = "${state.book?.views} views",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 18.sp,
                        )
                    }

                }


                // Book description
                Text(
                    text = state.book?.description.orEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify,
                )


                // Genres
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    state.book?.genres?.forEach {genre ->
                        AssistChip(
                            onClick = { },
                            modifier = Modifier.clickable { },
                            label = {Text(text = genre)},
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier,
                ) {

                    TextField(
                        value = folderName, onValueChange = {folderName = it},
                        label = {Text(text = "Folder name")},
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(end = 8.dp),
                    )

                    IconButton(
                        onClick = {
                            viewModel.onEvent(
                                BookEvent.AddToFolder(
                                    state.book!!.id,
                                    folderName,
                                ),
                            )
                            folderName = ""
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CreateNewFolder,
                            contentDescription = "Save to folder",
                        )
                    }
                }


                TabBar(
                    comments = state.comments,
                    chapters = state.chapters,
                    onAddComment = {viewModel.onEvent(BookEvent.AddComment(it))},
                    onDownloadChapter = {viewModel.onEvent(BookEvent.DownloadChapter(it.id))},
                    onOpenChapter = {onOpenBookReader(it.id)},
                    isloginInt = viewModel.isLogedIn,
                )
            }
        }
    }
}
