package almerti.egline.navigation


import almerti.egline.feature.book.bookCardGraph
import almerti.egline.feature.book.navigateToBookCardGraph
import almerti.egline.feature.login.loginGraph
import almerti.egline.feature.login.navigateToLoginGraph
import almerti.egline.feature.player.playerGraph
import almerti.egline.feature.reader.navigateToReaderGraph
import almerti.egline.feature.reader.readerGraph
import almerti.egline.feature.register.navigateToRegisterGraph
import almerti.egline.feature.register.registerGraph
import almerti.egline.navigation.navbar.MenuNavHost
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun RootNavHost(
    rootController : NavHostController = rememberNavController(),
) {
    NavHost(
        navController = rootController,
        startDestination = RootScreens.Library.route,
    ) {
        composable(RootScreens.BookReader.route) {
            BookReaderScreen(
                onNavigateToLibrary = {
                    rootController.navigate(RootScreens.Library.route)
                },
            )
        }
        loginGraph(
            onBackClick = {
                rootController.popBackStack()
            },
            onNavigateToRegisterGraph = {
                rootController.navigateToRegisterGraph()
            },
            onNavigateToMainPage = {
                rootController.popBackStack()
                rootController.navigate(RootScreens.Library.route)
            },
        )
        registerGraph(
            onBackClick = {
                rootController.popBackStack()
            },
            onNavigateToMainPage = {
                rootController.popBackStack()
                rootController.popBackStack()
                rootController.navigate(RootScreens.Library.route)
            },
        )
        playerGraph(
            onBack = {
                rootController.popBackStack()
            },
        )
        readerGraph(
            onBack = {
                rootController.popBackStack()
            },
            navController = rootController,
        )
        composable(RootScreens.Library.route) {
            MenuNavHost(
                onNavigateToLoginPage = {
                    rootController.navigateToLoginGraph()
                },
                onNavigateToBookPage = {
                    rootController.navigateToBookCardGraph(it)
                },
            )
        }
        bookCardGraph(
            onBack = {
                rootController.popBackStack()
            },
            onOpenBookReader = {
                rootController.navigateToReaderGraph(it)
            },
        )
    }
}

@Composable
fun BookReaderScreen(onNavigateToLibrary : () -> Unit) {
    Column(modifier = Modifier) {

        Text(text = "BookReaderScreen")
        Button(onClick = {onNavigateToLibrary()})
        {Text(text = "Go to Library")}
    }
}

sealed class RootScreens(val route : String) {
    object BookReader : RootScreens("book-reader")
    object Library : RootScreens("library")
}
