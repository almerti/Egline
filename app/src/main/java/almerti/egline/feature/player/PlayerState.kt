package almerti.egline.feature.player

import android.net.Uri

data class PlayerState(
    val isPlaying: Boolean = false,
    val currentPosition: Long = 0L,
    val sliderPosition: Long = 0L,
    val totalDuration: Long = 0L,
    val currentUri: Uri? = null
)
