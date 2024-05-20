package almerti.egline.feature.player

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val PLAYER_ROUTE = "player"

internal fun NavGraphBuilder.playerScreen(
    onBack: () -> Unit
) {
    composable(PLAYER_ROUTE) {
        val viewModel: PlayerViewModel = hiltViewModel<PlayerViewModel>()
        PlayerScreen(
            viewModel = viewModel,
            onBack = onBack,
        )
    }
}
