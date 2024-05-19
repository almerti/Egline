package almerti.egline.feature.profile.edit

import almerti.egline.R
import almerti.egline.ui.components.CustomIconButton
import almerti.egline.ui.components.CustomTextField
import almerti.egline.ui.components.FormButton
import almerti.egline.ui.components.PasswordField
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage

@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel,
    onNavigateToBack: () -> Unit
) {
    val user = viewModel.userState.collectAsState(null)
    val state = viewModel.state

    if (user.value == null)
        CircularProgressIndicator()
    else {
        viewModel.initState()
        Column(
            modifier = Modifier
                .padding(
                    top = 24.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 24.dp,
                )
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Start,
            ) {
                CustomIconButton(
                    onClick = onNavigateToBack,
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    size = 36.dp,
                )
            }
            Button(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp),
                onClick = {},
                contentPadding = PaddingValues(0.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
            ) {
                SubcomposeAsyncImage(
                    model = if (state.avatar.isEmpty())
                        R.drawable.ic_no_cover
                    else state.avatar,
                    loading = {CircularProgressIndicator()},
                    contentDescription = "User avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .clip(shape = CircleShape),
                )
            }
            CustomTextField(
                // email field
                labelText = stringResource(id = R.string.email_label),
                icon = {Icon(Icons.Outlined.Email, contentDescription = null)},
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(EditProfileEvent.EmailChanged(it))
                },
                isError = state.emailError != null,
                supportingText = state.emailError,
                paddingTop = 24.dp,
            )
            CustomTextField(
                // username field
                labelText = stringResource(id = R.string.username_label),
                icon = {Icon(Icons.Outlined.Person, contentDescription = null)},
                value = state.displayName,
                onValueChange = {
                    viewModel.onEvent(EditProfileEvent.DisplayNameChanged(it))
                },
                isError = state.displayNameError != null,
                supportingText = state.displayNameError,
            )
            PasswordField(
                // password field
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(EditProfileEvent.PasswordChanged(it))
                },
                isError = state.passwordError != null,
                supportingText = state.passwordError,
                label = stringResource(id = R.string.password_label),
            )
            PasswordField(
                // new password field
                value = state.newPassword,
                onValueChange = {
                    viewModel.onEvent(EditProfileEvent.NewPasswordChanged(it))
                },
                isError = state.newPasswordError != null,
                supportingText = state.newPasswordError,
                label = stringResource(id = R.string.new_password_label),
            )
            FormButton(
                text = stringResource(id = R.string.profile_edit_button),
                onClick = {
                    viewModel.onEvent(EditProfileEvent.Submit)
                },
            )
        }
    }
}
