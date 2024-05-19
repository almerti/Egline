package almerti.egline.feature.login

import almerti.egline.R
import almerti.egline.ui.components.AuthBottomMessage
import almerti.egline.ui.components.CustomIconButton
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.CircularProgressIndicator
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
                .padding(
                    top = it.calculateTopPadding() + 16.dp,
                    end = 16.dp,
                    bottom = it.calculateBottomPadding() + 16.dp,
                    start = 16.dp,
                )
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
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
                CustomIconButton(
                    onClick = onBackClick,
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    size = 36.dp,
                )
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
                    icon = {Icon(Icons.Outlined.Email, contentDescription = null)},
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
                    label = stringResource(id = R.string.password_label),
                )
                if (!viewModel.isLoginPressed)
                    FormButton(
                        text = stringResource(id = R.string.login_header),
                        onClick = {
                            viewModel.onEvent(LoginFormEvent.Submit)
                        },
                    )
                else
                    Row(
                        modifier = Modifier.padding(
                            top = 14.dp,
                            bottom = 14.dp,
                        ),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        CircularProgressIndicator()
                    }
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
