package almerti.egline.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormButton(
    text: String
) {
    FilledTonalButton(
        modifier = Modifier.padding(top = 20.dp),
        contentPadding = PaddingValues(
            start = 50.dp,
            end = 50.dp,
            top = 14.dp,
            bottom = 14.dp,
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.onBackground,
        ),
        onClick = { /*TODO*/},
        content = {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontWeight = FontWeight.Bold,
                ),
            )
        },
    )
}
