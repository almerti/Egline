package almerti.egline.ui.components

import almerti.egline.R
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    labelText: String,
    icon: Int
) {
    val inputText = remember {mutableStateOf("")}

    OutlinedTextField(
        modifier = Modifier.padding(bottom = 24.dp),
        value = inputText.value,
        label = {Text(text = labelText)},
        trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(icon),
                contentDescription = null,
            )
        },
        onValueChange = {newText -> inputText.value = newText},
    )
}
