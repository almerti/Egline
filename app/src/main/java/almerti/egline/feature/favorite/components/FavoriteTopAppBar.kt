package almerti.egline.feature.favorite.components

import almerti.egline.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteTopAppBar(
    modifier : Modifier = Modifier,
    onClick : () -> Unit = {}
) {
    TopAppBar(
        title = {Text(text = stringResource(id = R.string.favorite_header))},
        modifier = Modifier,
        actions = {

            IconButton(
                onClick = onClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                )
            }
        },
    )
}

@Preview(name = "TopAppBar")
@Composable
private fun PreviewTopAppBar() {
    FavoriteTopAppBar()
}
