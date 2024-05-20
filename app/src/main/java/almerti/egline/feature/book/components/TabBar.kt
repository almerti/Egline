package almerti.egline.feature.book.components

import almerti.egline.data.model.Chapter
import almerti.egline.data.model.Comment
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.outlined.DownloadForOffline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabBar(
    comments : List<Comment>,
    chapters : List<Chapter>,
) {
    var state by remember {mutableIntStateOf(0)}
    var text by remember {mutableStateOf("")}
    val titles = listOf("Comments", "Chapters")
    Column {
        SecondaryTabRow(selectedTabIndex = state) {
            titles.forEachIndexed {index, title ->
                Tab(
                    selected = state == index,
                    onClick = {state = index},
                    text = {Text(text = title, maxLines = 2, overflow = TextOverflow.Ellipsis)},
                )
            }
        }
        if (state == 0) {
            CommentList(comments = comments)
            TextField(
                value = text,
                onValueChange = {text = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                placeholder = {Text(text = "Write a comment...")},
                trailingIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Comment,
                        contentDescription = null,
                    )
                },
                label = {Text(text = "New Comment")},
            )
        } else {
            ChapterList(chapters = chapters)
        }
    }
}

@Composable
fun ChapterList(chapters : List<Chapter>) {
    for (chapter in chapters) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
                .clickable(onClick = { /*TODO*/}),
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier,
            ) {
                Text(text = "${chapter.number} - ${chapter.title}")
                Text(
                    text = chapter.date.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Thin,
                )
            }
            IconButton(onClick = { /*TODO*/}) {
                Icon(
                    imageVector = Icons.Outlined.DownloadForOffline,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
fun CommentList(comments : List<Comment>) {
    for (comment in comments) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier,
            ) {

                Text(
                    text = comment.text,
                    fontSize = 16.sp,
                )
                if (comment.chapterId != null) {
                    Text(
                        text = " add comment in chapter  ${comment.chapterId}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Thin,
                    )

                }
            }
        }
        HorizontalDivider()
    }
}
