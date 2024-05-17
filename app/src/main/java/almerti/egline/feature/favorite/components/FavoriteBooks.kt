package almerti.egline.feature.favorite.components

import almerti.egline.R
import almerti.egline.feature.favorite.BookItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FavoriteBooks(
    books: List<BookItem>,
    navigateToBookPage: (id: Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
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
    bookItem: BookItem,
    navigateToBookPage: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(250.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(20.dp)),
    ) {
        Button(
            onClick = navigateToBookPage,
            shape = RectangleShape,
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Transparent,
            ),
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
            )
        }
        Text(
            text = bookItem.bookTitle,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
            ),
        )
    }
}
