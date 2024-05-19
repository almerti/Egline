package almerti.egline.feature.favorite.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetRow(
    modifier : Modifier = Modifier,
    title : String,
    icon : ImageVector,
    onClick : () -> Unit,
    titleStyle : TextStyle = MaterialTheme.typography.bodyMedium,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {onClick()}
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            style = titleStyle,
        )
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
    }
}

@Preview(name = "BottomSheetRow")
@Composable
private fun PreviewBottomSheetRow() {
    BottomSheetRow(
        title = "Save",
        icon = Icons.Default.Add,
        onClick = {},
        titleStyle = MaterialTheme.typography.titleLarge,
    )
}
