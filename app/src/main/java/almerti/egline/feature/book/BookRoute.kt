package almerti.egline.feature.book

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

private const val BOOK_ID_ARG = "bookId"
const val BOOK_CARD_GRAPH_ROUTE = "book-card-graph/{$BOOK_ID_ARG}"

internal data class BookCardArgs(val bookId : Int) {
    constructor(savedStateHandle : SavedStateHandle) : this(
        bookId = checkNotNull(savedStateHandle[BOOK_ID_ARG]).toString().toInt(),
    )
}


fun NavGraphBuilder.bookCardGraph() {
    navigation(
        startDestination = BOOK_ROUTE,
        route = BOOK_CARD_GRAPH_ROUTE,
    ) {
        bookScreen()
    }
}

fun NavController.navigateToBookCardGraph(bookId : Int) {
    navigate(BOOK_CARD_GRAPH_ROUTE.replace("{$BOOK_ID_ARG}", bookId.toString()))
}
