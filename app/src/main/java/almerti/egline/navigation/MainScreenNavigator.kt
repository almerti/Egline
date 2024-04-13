package almerti.egline.navigation


import almerti.egline.feature.main.MainActions
import almerti.egline.feature.main.MainScreen
import almerti.egline.feature.main.MainState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    onOpenBookInfoScreen: (bookId: String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            MainScreen(
                state = MainState(),
                actions = MainActions(
                    //some test
                )
            )
        }
    }
}
