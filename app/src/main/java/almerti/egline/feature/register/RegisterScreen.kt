package almerti.egline.feature.register

import almerti.egline.R
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
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onBackClick: () -> Unit,
    onNavigateToMainPage: () -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val state = viewModel.state
            val context = LocalContext.current
            LaunchedEffect(key1 = context) {
                viewModel.registerValidationEvents.collect {event ->
                    when (event) {
                        is RegisterViewModel.RegisterValidationEvent.Success -> {
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
                    imageVector = Icons.Outlined.ArrowBackIosNew,
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
                    icon = {Icon(Icons.Outlined.Email, contentDescription = null)},
                    value = state.email,
                    onValueChange = {
                        viewModel.onEvent(RegisterFormEvent.EmailChanged(it))
                    },
                    isError = state.emailError != null,
                    supportingText = state.emailError,
                )
                CustomTextField(
                    // username field
                    labelText = stringResource(id = R.string.username_label),
                    icon = {Icon(Icons.Outlined.Person, contentDescription = null)},
                    value = state.displayName,
                    onValueChange = {
                        viewModel.onEvent(RegisterFormEvent.DisplayNameChanged(it))
                    },
                    isError = state.displayNameError != null,
                    supportingText = state.displayNameError,
                )
                PasswordField(
                    value = state.password,
                    onValueChange = {
                        viewModel.onEvent(RegisterFormEvent.PasswordChanged(it))
                    },
                    isError = state.passwordError != null,
                    supportingText = state.passwordError,
                    label = stringResource(id = R.string.password_label),
                )
                FormButton(
                    text = stringResource(id = R.string.register_header),
                    onClick = {
                        viewModel.onEvent(RegisterFormEvent.Submit)
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        viewModel = hiltViewModel<RegisterViewModel>(),
        onBackClick = {},
        onNavigateToMainPage = {},
    )
}
