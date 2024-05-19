package almerti.egline.feature.profile.main

import almerti.egline.R
import almerti.egline.feature.favorite.components.FavoriteBooks
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Edit
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
import androidx.compose.ui.res.stringResource
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
    val state = viewModel.state

    if (user.value == null)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator()
        }
    else
        Column(
            modifier = Modifier
                .padding(
                    top = 36.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                )
                .fillMaxSize(),
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
                    onClick = if (user.value?.id == -1)
                        onNavigateToLoginPage
                    else onNavigateToEditPage,
                    imageVector = Icons.Outlined.Edit,
                    size = 32.dp,
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
                    onClick = if (user.value?.id == -1)
                        onNavigateToLoginPage
                    else {
                        viewModel.onLogout()
                    },
                    imageVector = if (user.value?.id == -1)
                        Icons.AutoMirrored.Outlined.Login
                    else
                        Icons.AutoMirrored.Outlined.Logout,
                    size = 32.dp,
                )
            }
            if (user.value?.id == -1)
                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = "No Info",
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Bold,
                    ),
                )
            else {
                Text(
                    text = user.value?.displayName!!,
                    style = TextStyle(
                        fontSize = 28.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.ExtraBold,
                    ),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Text(
                        modifier = Modifier.padding(top = 36.dp, bottom = 16.dp),
                        text = stringResource(id = R.string.profile_last_saved_books),
                        style = TextStyle(
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                }
                if (state.bookList == null) {
                    CircularProgressIndicator()
                } else if (state.bookList.isNotEmpty())
                    FavoriteBooks(
                        books = state.bookList,
                        navigateToBookPage = {},
                    )
                else
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 12.dp),
                            text = stringResource(id = R.string.favorite_no_books_message),
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontWeight = FontWeight.Medium,
                            ),
                        )
                    }
            }
        }
}
