package almerti.egline.feature.favorite.components

import almerti.egline.data.model.Folder
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FavoriteFoldersSlider(
    folders : List<Folder>,
    current : Folder,
    changeCurrentFolder : (folder : Folder) -> Unit
) {
    val activeColor = MaterialTheme.colorScheme.primary
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
    ) {
        LazyRow {
            items(folders.size) {index ->
                val isCurrent = folders[index].folderName == current.folderName
                Box(
                    modifier = Modifier
                        .padding(
                            end = 12.dp,
                        )
                        .drawBehind {
                            val borderSize = 5.dp.toPx()
                            if (isCurrent)
                                drawLine(
                                    color = activeColor,
                                    start = Offset(0f + (size.width / 2 - 15), size.height),
                                    end = Offset(0f + (size.width / 2 + 15), size.height),
                                    strokeWidth = borderSize,
                                    cap = StrokeCap.Round,
                                )
                        },
                ) {
                    TextButton(
                        onClick = {
                            changeCurrentFolder(folders[index])
                        },
                        contentPadding = PaddingValues(0.dp),
                        shape = RectangleShape,
                    ) {
                        Text(
                            text = folders[index].folderName,
                            style = TextStyle(
                                fontWeight = if (isCurrent)
                                    FontWeight.Black
                                else FontWeight.Medium,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                            ),
                        )
                    }
                }
            }
        }
    }
}
