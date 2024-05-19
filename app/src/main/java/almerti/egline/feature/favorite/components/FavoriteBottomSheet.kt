package almerti.egline.feature.favorite.components

import almerti.egline.feature.favorite.FavoriteState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FavoriteBottomSheet(
    modifier : Modifier = Modifier,
    sheetState : SheetState,
    onDismissRequest : () -> Unit = {},
    favoriteState : FavoriteState,
    onEditFolder : (String, String) -> Unit = {_, _ -> {}},
    onAddNewFolder : (String) -> Unit = {},
    onDeleteFolder : (String) -> Unit = {},
) {
    var isEditMode by remember {mutableStateOf(false)}
    var editingFolder by remember {mutableStateOf<String>("")}
    var newFolderName by remember {mutableStateOf("")}
    var oldFolderName by remember {mutableStateOf("")}
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = Modifier
            .fillMaxWidth()
            .imePadding(),
    ) {
        Text(
            text = "Edit folders",
            modifier = Modifier.padding(start = 20.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        if (isEditMode) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    value = editingFolder,
                    onValueChange = {editingFolder = it},
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {Text("New folder name")},
                )
                Spacer(modifier = Modifier.width(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(horizontal = 16.dp),
                ) {

                    IconButton(
                        onClick = {
                            isEditMode = false
                            editingFolder = ""
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null,
                        )
                    }
                    Spacer(modifier = Modifier.width(70.dp))
                    OutlinedButton(
                        onClick = {
                            onEditFolder(oldFolderName, editingFolder)
                            isEditMode = false
                            editingFolder = ""
                            oldFolderName = ""
                        },
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        Text(text = "change")
                    }
                    Spacer(modifier = Modifier.width(30.dp))

                    Button(
                        onClick = {onDeleteFolder(editingFolder)},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.onError,
                        ),
                    ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                        Text(text = "Delete")
                    }

                }
            }

        } else {
            Column(
                modifier = Modifier
                    .height(180.dp)
                    .verticalScroll(rememberScrollState())
                    .weight(weight = 1f, fill = false),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {

                for (folder in favoriteState.folders!!) {
                    BottomSheetRow(
                        title = folder.folderName,
                        icon = Icons.Default.EditNote,
                        onClick = {
                            isEditMode = true
                            editingFolder = folder.folderName
                            oldFolderName = folder.folderName
                        },
                    )
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            OutlinedTextField(
                value = newFolderName,
                onValueChange = {newFolderName = it},
                label = {Text(text = "new Folder")},
                modifier = modifier
                    .fillMaxWidth()
                    .padding(15.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                OutlinedButton(
                    onClick = {
                        onAddNewFolder(newFolderName)
                        newFolderName = ""
                    },
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    Text(text = "add")
                }
            }
        }

    }
}
