package almerti.egline.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@SuppressLint("UnrememberedMutableState")
@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    supportingText: String?,
    label: String
) {
    var passwordVisibility: Boolean by remember {mutableStateOf(false)}

    OutlinedTextField(
        modifier = Modifier.padding(bottom = 12.dp),
        value = value,
        label = {Text(text = label)},
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisibility)
                Icons.Outlined.VisibilityOff
            else Icons.Outlined.Visibility

            IconButton(
                onClick = {passwordVisibility = !passwordVisibility},
            ) {
                Icon(
                    imageVector = image,
                    contentDescription = null,
                )
            }
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
