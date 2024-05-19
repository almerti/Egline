package almerti.egline.feature.favorite.components

import almerti.egline.R
import almerti.egline.feature.favorite.BookItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoPhotography
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage

@Composable
fun FavoriteBooks(
    books : List<BookItem>,
    navigateToBookPage : (id : Int) -> Unit
) {
    Column {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(books) {item ->
                BookCard(
                    bookItem = item,
                    navigateToBookPage = {
                        navigateToBookPage(item.bookId)
                    },
                )
            }
        }
    }
}

@Composable
private fun BookCard(
    bookItem : BookItem,
    navigateToBookPage : () -> Unit
) {
    Column {
        Button(
            onClick = navigateToBookPage,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = MaterialTheme.colorScheme.onSurface,
                containerColor = Color.Transparent,
            ),
            contentPadding = PaddingValues(0.dp),
        ) {
            SubcomposeAsyncImage(
                model = if (bookItem.bookCover.isEmpty())
                    Icon(
                        imageVector = Icons.Filled.NoPhotography,
                        contentDescription = null,
                        modifier = Modifier
                            .height(200.dp)
                            .size(150.dp)
                            .background(color = MaterialTheme.colorScheme.surfaceContainer),
                    )
                else bookItem.bookCover,
                loading = {
                    CircularProgressIndicator()
                },
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp),
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            text = if (bookItem.bookTitle.length > 40)
                bookItem.bookTitle.substring(0, 38) + "…"
            else bookItem.bookTitle,
            maxLines = 2,
            style = TextStyle(
                lineHeight = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
    }
}

@Composable
@Preview
private fun BookCardPreview() {
    BookCard(
        bookItem = BookItem(1, "Hekki", ByteArray(1)),
        navigateToBookPage = {},
    )
}

