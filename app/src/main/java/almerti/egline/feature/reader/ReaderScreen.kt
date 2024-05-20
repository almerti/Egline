package almerti.egline.feature.reader

import almerti.egline.feature.reader.components.ReaderBottomSheet
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Headset
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderScreen(
    viewModel: ReaderViewModel,
    onBack: () -> Unit,
    onNavigateToAudio: (chapterId: Int) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember {mutableStateOf(false)}

    if (viewModel.text == null)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator()
        }
    else
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text(text = viewModel.title!!)},
                    modifier = Modifier,
                    navigationIcon = {
                        IconButton(
                            onClick = onBack,
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                showBottomSheet = !showBottomSheet
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.TextFields,
                                contentDescription = null,
                            )
                        }
                        IconButton(
                            onClick = {
                                onNavigateToAudio(viewModel.chapterId)
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Headset,
                                contentDescription = null,
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    ),
                )
            },
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        top = it.calculateTopPadding() + 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = it.calculateBottomPadding() + 16.dp,
                    )
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    text = viewModel.text!!,
                    style = TextStyle(
                        fontSize = viewModel.fontSize.sp,
                    ),
                )
            }
            if (showBottomSheet) {
                ReaderBottomSheet(
                    Modifier,
                    sheetState,
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    onChangeFontSize = {
                        viewModel.changeFontSize(it)
                    },
                    currentValue = viewModel.fontSize,
                )
            }
        }
}
