package almerti.egline.feature.profile.main

import almerti.egline.R
import almerti.egline.ui.components.CustomIconButton
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.outlined.DoorFront
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProfileMainScreen(
    viewModel: ProfileMainViewModel,
    onNavigateToLoginPage: () -> Unit,
    onNavigateToEditPage: () -> Unit
) {
    val user = viewModel.userState.collectAsState(initial = null)

    if (user.value == null)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator()
        }
    else if (user.value?.id == -1)
        Button(onClick = onNavigateToEditPage) {
            Text(text = "Login")
        }
    else
        Column(
            modifier = Modifier
                .padding(
                    top = 24.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                )
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CustomIconButton(
                    onClick = onNavigateToEditPage,
                    imageVector = Icons.Outlined.Edit,
                    size = 40.dp,
                )
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(150.dp)
                        .background(Color.Companion.Transparent, shape = CircleShape),
                ) {
                    SubcomposeAsyncImage(
                        model = if (user.value?.avatar?.isEmpty() == true)
                            R.drawable.ic_no_cover
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
                CustomIconButton(
                    onClick = onNavigateToEditPage,
                    imageVector = Icons.Outlined.DoorFront,
                    size = 40.dp,
                )
            }
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = user.value?.displayName!!,
                style = TextStyle(
                    fontSize = 28.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Black,
                ),
            )
        }
}
