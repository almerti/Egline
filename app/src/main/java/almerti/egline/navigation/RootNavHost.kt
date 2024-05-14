package almerti.egline.navigation


import almerti.egline.feature.login.loginGraph
import almerti.egline.feature.login.navigateToLoginGraph
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
    modifier: Modifier = Modifier,
    rootController: NavHostController = rememberNavController(),
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

        loginGraph()

        composable(RootScreens.Library.route) {
            MenuNavHost(
                onNavigateToLoginPage = {
                    rootController.navigateToLoginGraph()
                },
            )
        }
    }
}

@Composable
fun BookReaderScreen(onNavigateToLibrary: () -> Unit) {
    Column(modifier = Modifier) {

        Text(text = "BookReaderScreen")
        Button(onClick = {onNavigateToLibrary()})
        {Text(text = "Go to Library")}
    }
}

sealed class RootScreens(val route: String) {
    object BookReader : RootScreens("book-reader")
    object Library : RootScreens("library")
}
