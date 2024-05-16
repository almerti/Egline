package almerti.egline.feature.login

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onBackClick: () -> Unit,
    onNavigateToRegisterGraph: () -> Unit,
    onNavigateToMainPage: () -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val state = viewModel.state
            val context = LocalContext.current
            LaunchedEffect(key1 = context) {
                viewModel.loginValidationEvents.collect {event ->
                    when (event) {
                        is LoginViewModel.LoginValidationEvent.Success -> {
                            onNavigateToMainPage()
                        }
                    }
                }
            }
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
                        .height(180.dp)
                        .width(160.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    modifier = Modifier.padding(bottom = 36.dp),
                    text = stringResource(id = R.string.login_header),
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
                    value = state.email,
                    onValueChange = {
                        viewModel.onEvent(LoginFormEvent.EmailChanged(it))
                    },
                    isError = state.emailError != null,
                    supportingText = state.emailError,
                )
                PasswordField(
                    value = state.password,
                    onValueChange = {
                        viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
                    },
                    isError = state.passwordError != null,
                    supportingText = state.passwordError,
                )
                FormButton(
                    text = stringResource(id = R.string.login_header),
                    onClick = {
                        viewModel.onEvent(LoginFormEvent.Submit)
                    },
                )
                Spacer(modifier = Modifier.weight(1f))
                AuthBottomMessage(
                    text1 = stringResource(id = R.string.register_message_part1),
                    text2 = stringResource(id = R.string.register_message_part2),
                    navigateToGraph = onNavigateToRegisterGraph, // navigate to register graph
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        viewModel = hiltViewModel<LoginViewModel>(),
        onBackClick = {},
        onNavigateToRegisterGraph = {},
        onNavigateToMainPage = {},
    )
}
