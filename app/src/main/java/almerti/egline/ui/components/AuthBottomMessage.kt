package almerti.egline.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthBottomMessage(
    text1: String,
    text2: String,
    navigateToGraph: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 42.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = text1,
        )
        TextButton(
            modifier = Modifier.height(22.dp),
            onClick = navigateToGraph,
            contentPadding = PaddingValues(
                start = 5.dp,
                top = 1.dp,
                end = 5.dp,
                bottom = 0.dp,
            ),
            shape = RectangleShape,
            content = {
                Text(
                    text = text2,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                    ),
                )
            },
        )
    }
}
