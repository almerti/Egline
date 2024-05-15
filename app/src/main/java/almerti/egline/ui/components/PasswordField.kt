package almerti.egline.ui.components

import almerti.egline.R
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PasswordField() {
    var passwordVisibility: Boolean by remember {mutableStateOf(false)}
    val inputText = remember {mutableStateOf("")}
    val icon = remember {mutableIntStateOf(R.drawable.baseline_visibility_24)}

    OutlinedTextField(
        modifier = Modifier.padding(bottom = 24.dp),
        value = inputText.value,
        placeholder = {Text(text = stringResource(id = R.string.password_field))},
        label = {Text(text = stringResource(id = R.string.password_label))},
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(
                onClick = {
                    passwordVisibility = !passwordVisibility
                    if (passwordVisibility) {
                        icon.intValue = R.drawable.baseline_visibility_off_24
                    } else {
                        icon.intValue = R.drawable.baseline_visibility_24
                    }
                },
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(icon.intValue),
                    contentDescription = null,
                )
            }
        },
        onValueChange = {newText -> inputText.value = newText},
    )
}
