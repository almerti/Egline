package almerti.egline.navigation


import almerti.egline.screens.mainScreen.MainScreen
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
            MainScreen()
        }
        composable(Screen.Catalog.route) {
            CatalogScreen(
                onOpenBookInfoScreen = onOpenBookInfoScreen
            )
        }
        composable(Screen.Notifications.route) {
            NotificationsScreen(
                onOpenBookInfoScreen = onOpenBookInfoScreen
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                },
                onNavigateToAboutUs = {
                    navController.navigate(Screen.AboutUs.route)
                }
            )
        }
        composable(Screen.Profile.route) {
            ProfileScreen()
        }
        composable(Screen.AboutUs.route) {
            AboutUsScreen()
        }
    }
}
