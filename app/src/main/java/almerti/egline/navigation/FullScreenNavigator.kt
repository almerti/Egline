package almerti.egline.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun FullscreenNavigationGraph(
    navController: NavHostController,
    onBack: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.BookInfo.route
    ) {

        }
    }