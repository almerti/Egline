package almerti.egline.feature.profile.edit

import almerti.egline.R
import almerti.egline.ui.components.CustomIconButton
import almerti.egline.ui.components.CustomTextField
import almerti.egline.ui.components.PasswordField
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    viewModel : EditProfileViewModel,
    onNavigateToBack : () -> Unit
) {
    val user = viewModel.userState.collectAsState(null)
    val state = viewModel.state
    val context = LocalContext.current
    var openAlertDialog by remember {mutableStateOf(false)}

    if (openAlertDialog) {
        AlertDialogExample(
            onDismissRequest = {
                openAlertDialog = false
            },
            onConfirmation = {
                openAlertDialog = false
                onNavigateToBack()
            },
            icon = Icons.Outlined.Warning,
        )
    }


    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            if (it != null) {
                viewModel.selectedImageUri.value = it
                val bytes = readBytes(context, viewModel.selectedImageUri.value!!)
                viewModel.onEvent(EditProfileEvent.AvatarChanged(bytes!!))
            }
        },
    )

    if (user.value == null)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator()
        }
    else {
        LaunchedEffect(key1 = context) {
            viewModel.editProfileValidationEvents.collect {event ->
                when (event) {
                    is EditProfileViewModel.EditProfileValidationEvent.Success -> {
                        onNavigateToBack()
                    }

                    is EditProfileViewModel.EditProfileValidationEvent.Failed -> {
                        Toast.makeText(
                            context,
                            "Connecting to server error",
                            Toast.LENGTH_LONG,
                        ).show()
                    }
                }
            }
        }
        viewModel.initState()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text(text = stringResource(id = R.string.edit_page_header))},
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                openAlertDialog = true
                            },
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    },
                    actions = {
                        CustomIconButton(
                            onClick = {
                                viewModel.onEvent(EditProfileEvent.Submit)
                            },
                            imageVector = Icons.Outlined.Save,
                            size = 28.dp,
                            paddingValues = PaddingValues(
                                end = 8.dp,
                            ),
                        )
                    },
                )
            },
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        top = it.calculateTopPadding() + 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = it.calculateBottomPadding() + 8.dp,
                    )
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp),
                    onClick = {
                        imagePickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    },
                    contentPadding = PaddingValues(0.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                    ),
                ) {
                    SubcomposeAsyncImage(
                        model = if (user.value?.avatar?.isEmpty()!! && viewModel.selectedImageUri.value == null)
                            R.drawable.ic_no_cover
                        else if (viewModel.selectedImageUri.value != null)
                            viewModel.selectedImageUri.value
                        else user.value?.avatar,
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
                if (viewModel.isEditPressed.value) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Throws(IOException::class)
private fun readBytes(context : Context, uri : Uri) : ByteArray? =
    context.contentResolver.openInputStream(uri)?.buffered()?.use {it.readBytes()}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AlertDialogExample(
    onDismissRequest : () -> Unit,
    onConfirmation : () -> Unit,
    icon : ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = stringResource(id = R.string.edit_alert_title))
        },
        text = {
            Text(text = stringResource(id = R.string.edit_alert_message))
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                },
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                },
            ) {
                Text("Dismiss")
            }
        },
    )
}
