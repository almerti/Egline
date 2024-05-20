package almerti.egline.feature.player

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val CHAPTER_ID_ARG = "chapterId"
const val PLAYER_GRAPH_ROUTE = "player_route/{$CHAPTER_ID_ARG}"

fun NavController.navigateToPlayerGraph(chapterId: Int) {
    navigate(PLAYER_GRAPH_ROUTE.replace("{$CHAPTER_ID_ARG}", chapterId.toString()))
}

internal data class PlayerChapterArg(val chapterId: Int) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        chapterId = checkNotNull(savedStateHandle[CHAPTER_ID_ARG]).toString().toInt(),
    )
}

fun NavGraphBuilder.playerGraph(
    onBack: () -> Unit,
) {
    navigation(
        route = PLAYER_GRAPH_ROUTE,
        startDestination = PLAYER_ROUTE,
    ) {
        playerScreen(
            onBack = onBack,
        )
    }
}
