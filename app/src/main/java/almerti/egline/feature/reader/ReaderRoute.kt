package almerti.egline.feature.reader

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val CHAPTER_ID_ARG = "chapterId"
const val READER_GRAPH_ROUTE = "reader_route/{$CHAPTER_ID_ARG}"

fun NavController.navigateToReaderGraph(chapterId: Int) {
    navigate(
        READER_GRAPH_ROUTE.replace(
            "{${almerti.egline.feature.player.CHAPTER_ID_ARG}}",
            chapterId.toString(),
        ),
    )
}

internal data class ReaderChapterArg(val chapterId: Int) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        chapterId = checkNotNull(savedStateHandle[almerti.egline.feature.player.CHAPTER_ID_ARG]).toString()
            .toInt(),
    )
}

fun NavGraphBuilder.readerGraph(
    onBack: () -> Unit,
    navController: NavController
) {
    navigation(
        route = READER_GRAPH_ROUTE,
        startDestination = READER_ROUTE,
    ) {
        readerScreen(
            onBack = onBack,
            navController = navController,
        )
    }
}



