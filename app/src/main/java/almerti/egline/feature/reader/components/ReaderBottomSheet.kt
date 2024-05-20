package almerti.egline.feature.reader.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    onDismissRequest: () -> Unit = {},
    onChangeFontSize: (Int) -> Unit,
    currentValue: Int
) {
    var sliderPosition by remember {mutableFloatStateOf(currentValue.toFloat())}

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        modifier = Modifier
            .fillMaxWidth()
            .imePadding(),
    ) {
        Text(
            text = "Change font size",
            modifier = Modifier.padding(start = 20.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Slider(
            value = sliderPosition,
            valueRange = 18f..28f,
            steps = 5,
            onValueChange = {
                sliderPosition = it
                onChangeFontSize(it.toInt())
            },
            modifier = Modifier.padding(
                start = 20.dp,
                top = 16.dp,
                end = 20.dp,
                bottom = 16.dp,
            ),
        )
        Text(
            text = currentValue.toString(),
            modifier = Modifier.padding(
                start = 20.dp,
                bottom = 16.dp,
            ),
        )
    }
}
