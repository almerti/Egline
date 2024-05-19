package almerti.egline.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    labelText: String,
    icon: @Composable () -> Unit,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    supportingText: String?,
    paddingTop: Dp = 0.dp
) {
    OutlinedTextField(
        modifier = Modifier.padding(bottom = 24.dp, top = paddingTop),
        value = value,
        label = {Text(text = labelText)},
        trailingIcon = icon,
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
