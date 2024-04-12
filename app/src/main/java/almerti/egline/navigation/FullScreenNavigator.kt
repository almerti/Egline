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
        composable(Screen.BookInfo.route) {
            BookInfoScreen(
                onOpenReadingScreen = {
                    navController.navigate(Screen.Reading.route)
                },
                onOpenAudioPlayerScreen = {
                    navController.navigate(Screen.AudioPlayer.route)
                }
            )
        }
        composable(Screen.Reading.route) {
            ReadingScreen(
                onBack = onBack
            )
        }
        composable(Screen.AudioPlayer.route) {
            AudioPlayerScreen(
                onBack = onBack
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onLogin = {
                    // Handle login logic
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                },
                onRegister = {
                    // Handle registration logic
                    navController.popBackStack()
                }
            )
        }
    }
}