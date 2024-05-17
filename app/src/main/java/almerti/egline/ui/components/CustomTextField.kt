package almerti.egline.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    labelText: String,
    icon: Int,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    supportingText: String?
) {
    OutlinedTextField(
        modifier = Modifier.padding(bottom = 24.dp),
        value = value,
        label = {Text(text = labelText)},
        trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
            )
        },
        onValueChange = onValueChange,
        isError = isError,
        supportingText = {
            if (supportingText != null) {
                Text(
                    text = supportingText,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        },
    )
}
