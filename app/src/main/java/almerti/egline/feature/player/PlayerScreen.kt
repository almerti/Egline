package almerti.egline.feature.player

import almerti.egline.ui.components.CustomIconButton
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.FastForward
import androidx.compose.material.icons.rounded.FastRewind
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import coil.compose.SubcomposeAsyncImage
import kotlinx.coroutines.delay

@OptIn(UnstableApi::class)
@Composable
fun PlayerScreen(
    viewModel: PlayerViewModel,
    onBack: () -> Unit
) {
    val player = ExoPlayer.Builder(LocalContext.current).build()

    if (viewModel.mediaItem != null) {
        LaunchedEffect(Unit) {
            player.addMediaItem(viewModel.mediaItem!!)
        }
        player.prepare()

        val isPlaying = remember {
            mutableStateOf(false)
        }

        val currentPosition = remember {
            mutableLongStateOf(0L)
        }

        val sliderPosition = remember {
            mutableLongStateOf(0L)
        }

        val totalDuration = remember {
            mutableLongStateOf(0L)
        }

        LaunchedEffect(key1 = player.currentPosition, key2 = player.isPlaying) {
            delay(1000)
            currentPosition.longValue = player.currentPosition
        }

        LaunchedEffect(currentPosition) {
            sliderPosition.longValue = currentPosition.longValue
        }

        LaunchedEffect(player.duration) {
            if (player.duration > 0) {
                totalDuration.longValue = player.duration
            } else {
                System.out.println("SJFHLSFLJNBNSLKJNSFJNSBJNSKBJNKSJNO OIA U")
            }
        }

        Scaffold {
            Column(
                modifier = Modifier.padding(
                    top = it.calculateTopPadding() + 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = it.calculateBottomPadding() + 16.dp,
                ),
            ) {
                Row {
                    CustomIconButton(
                        onClick = onBack,
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        size = 28.dp,
                    )
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Column(
                        modifier = Modifier
                            .width(300.dp)
                            .height(400.dp)
                            .padding(
                                top = 24.dp,
                                bottom = 36.dp,
                            )
                            .shadow(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(16.dp),
                                clip = true,
                                spotColor = MaterialTheme.colorScheme.onSurface,
                                ambientColor = MaterialTheme.colorScheme.onTertiary,
                            ),
                    ) {
                        SubcomposeAsyncImage(
                            model = viewModel.cover,
                            contentDescription = null,
                            loading = {
                                CircularProgressIndicator()
                            },
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = RoundedCornerShape(16.dp)),
                        )
                    }
                    Text(
                        modifier = Modifier
                            .widthIn(min = 250.dp, max = 300.dp)
                            .padding(bottom = 16.dp),
                        text = viewModel.bookTitle,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        ),
                    )
                    Text(
                        modifier = Modifier
                            .widthIn(min = 250.dp, max = 300.dp)
                            .padding(bottom = 36.dp),
                        text = viewModel.chapterTitle,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        ),
                    )
                    /*Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = (currentPosition.longValue).convertToText(),
                            modifier = Modifier
                                .weight(1f)
                                .padding(8.dp),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = TextStyle(fontWeight = FontWeight.Bold),
                        )

                        val remainTime = totalDuration.longValue - currentPosition.longValue
                        Text(
                            text = if (remainTime > 0) remainTime.convertToText() else "00:00",
                            modifier = Modifier
                                .padding(8.dp),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = TextStyle(fontWeight = FontWeight.Bold),
                        )
                    }*/
                    /*TrackSlider(
                        value = sliderPosition.longValue.toFloat(),
                        onValueChange = {
                            sliderPosition.longValue = it.toLong()
                        },
                        onValueChangeFinished = {
                            currentPosition.longValue = sliderPosition.longValue
                            player.seekTo(sliderPosition.longValue)
                        },
                        songDuration = totalDuration.longValue.toFloat(),
                    )*/
                    // audio buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier.padding(end = 18.dp),
                        ) {
                            CustomIconButton(
                                onClick = {
                                    currentPosition.longValue -= 5000L
                                    player.seekTo(currentPosition.longValue)
                                },
                                imageVector = Icons.Rounded.FastRewind,
                                size = 36.dp,
                            )
                        }
                        CustomIconButton(
                            onClick = {
                                if (isPlaying.value) {
                                    player.pause()
                                } else {
                                    player.play()
                                }
                                isPlaying.value = player.isPlaying
                            },
                            imageVector = if (isPlaying.value)
                                Icons.Rounded.Pause
                            else Icons.Rounded.PlayArrow,
                            size = 72.dp,
                        )
                        Box(
                            modifier = Modifier.padding(start = 18.dp),
                        ) {
                            CustomIconButton(
                                onClick = {
                                    currentPosition.longValue += 5000L
                                    player.seekTo(currentPosition.longValue)
                                },
                                imageVector = Icons.Rounded.FastForward,
                                size = 36.dp,
                            )
                        }
                    }
                }
            }
        }
    }
}

/*private fun Long.convertToText(): String {
    val sec = this / 1000
    val minutes = sec / 60
    val seconds = sec % 60

    val minutesString = if (minutes < 10) {
        "0$minutes"
    } else {
        minutes.toString()
    }
    val secondsString = if (seconds < 10) {
        "0$seconds"
    } else {
        seconds.toString()
    }
    return "$minutesString:$secondsString"
}*/
