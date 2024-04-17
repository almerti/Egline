package almerti.egline.navigation


import almerti.egline.feature.main.MainActions
import almerti.egline.feature.main.MainScreen
import almerti.egline.feature.main.MainState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun FullscreenNavigationGraph(
    navController: NavHostController,
    modifer: Modifier = Modifier,
    startDestination: String = Screen.Main.route,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {

        composable(Screen.Main.route) {
            MainScreen(MainState(), MainActions())
        }
    }
}