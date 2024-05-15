package almerti.egline.feature.register

import almerti.egline.R
import almerti.egline.ui.components.AuthBottomMessage
import almerti.egline.ui.components.BackButton
import almerti.egline.ui.components.CustomTextField
import almerti.egline.ui.components.FormButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import almerti.egline.ui.components.PasswordField
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onBackClick: () -> Unit,
    onNavigateToLoginGraph: () -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
            ) {
                BackButton(onBackClick = onBackClick)
            }
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    modifier = Modifier
                        .height(220.dp)
                        .width(200.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    modifier = Modifier.padding(bottom = 36.dp),
                    text = stringResource(id = R.string.register_header),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 36.sp,
                        fontStyle = FontStyle.Normal,
                    ),
                )
                CustomTextField(
                    // email field
                    labelText = stringResource(id = R.string.email_label),
                    icon = R.drawable.baseline_email_24,
                )
                CustomTextField(
                    // username field
                    labelText = stringResource(id = R.string.username_label),
                    icon = R.drawable.baseline_person_24,
                )
                PasswordField()
                FormButton(
                    text = stringResource(id = R.string.register_header),
                )
                Spacer(modifier = Modifier.weight(1f))
                AuthBottomMessage(
                    text1 = stringResource(id = R.string.login_message_part1),
                    text2 = stringResource(id = R.string.login_message_part2),
                    navigateToGraph = onNavigateToLoginGraph,
                )
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        viewModel = RegisterViewModel(),
        onBackClick = {},
        onNavigateToLoginGraph = {},
    )
}
