package almerti.egline.ui.components

import almerti.egline.R
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun BackButton(
    onBackClick: () -> Unit
) {
    Button(
        modifier = Modifier.size(36.dp),
        onClick = onBackClick,
        content = {
            Icon(
                imageVector = Icons.Outlined.ArrowBackIosNew,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
            )
        },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        contentPadding = PaddingValues(2.dp),
    )
}
