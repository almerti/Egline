package almerti.egline.ui.components

import almerti.egline.data.model.Book
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage

@Composable
fun BookCard(
    book : Book,
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
                model = if (book.cover.isEmpty())
                    Icon(
                        imageVector = Icons.Filled.NoPhotography,
                        contentDescription = null,
                        modifier = Modifier
                            .height(200.dp)
                            .size(150.dp)
                            .background(color = MaterialTheme.colorScheme.surfaceContainer),
                    )
                else book.cover,
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
            text = if (book.title.length > 40)
                book.title.substring(0, 38) + "…"
            else book.title,
            maxLines = 2,
            style = TextStyle(
                lineHeight = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
    }
}
