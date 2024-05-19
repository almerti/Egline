package almerti.egline.feature.favorite.components

import almerti.egline.data.model.Folder
import almerti.egline.feature.favorite.FavoriteState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteBottomSheet(
    modifier : Modifier = Modifier,
    sheetState : SheetState,
    onDismissRequest : () -> Unit = {},
    favoriteState : FavoriteState,
    onChangeFolder : (Folder) -> Unit = {},
) {
    var isEditMode by remember {mutableStateOf(false)}
    var editingFolder by remember {mutableStateOf<Folder?>(null)}
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
    ) {
        if (isEditMode) {
            when (editingFolder) {
                null -> Unit // do nothing
                else -> {

                }
            }
        } else {
            for (folder in favoriteState.folders!!) {
                BottomSheetRow(
                    title = folder.folderName,
                    icon = Icons.Default.EditNote,
                    onClick = {isEditMode = true},
                )
            }
        }
    }
}


@Preview
@Composable
private fun FavoriteBottomSheetPreview() {
    var inputText by remember {mutableStateOf("")}

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = inputText,
            onValueChange = {inputText = it},
            modifier = Modifier.fillMaxWidth(),
            placeholder = {Text("New folder name")},
        )
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier,
        ) {
            Button(onClick = { /*TODO*/}) {

            }
            IconButton(onClick = { /*TODO*/}, colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError)) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }

}
