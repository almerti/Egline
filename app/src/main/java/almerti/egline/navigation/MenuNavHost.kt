package almerti.egline.navigation

import almerti.egline.feature.settings.navigateToSettingsGraph
import almerti.egline.feature.settings.settingsGraph
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Deck
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation

@Composable
@Preview
fun MenuNavHost(
    navController : NavHostController = rememberNavController(),
    modifier : Modifier = Modifier,
    onNavigateToLoginPage : () -> Unit
) {
    Scaffold(
        bottomBar = {BottomNavBar(navController)},
    ) {innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = LibraryScreens.Favorites.route,
        ) {
            navigation(
                route = LibraryScreens.Library.route,
                startDestination = LibraryScreens.Books.route,
            ) {

                composable(LibraryScreens.Books.route) {
                    BooksScreen(navController)
                }
                composable(
                    route = LibraryScreens.BookDetails.route,
                ) {backStackEntry ->
                    BookDetailsScreen(navController)
                }
            }

            composable(LibraryScreens.Favorites.route) {
                FavoritesScreen(navController)
            }

            composable(LibraryScreens.Profile.route) {
                ProfileScreen(navController, onNavigateToLoginPage)
            }
            settingsGraph()
        }
    }
}


@Composable
fun BottomNavBar(navController : NavHostController) {
    NavigationBar {
        NavigationBarItem(
            icon = {Icon(Icons.Outlined.Deck, contentDescription = null)},
            label = {Text("Favorites")},
            selected = navController.currentDestination?.route == LibraryScreens.Favorites.route,
            onClick = {navController.navigate(LibraryScreens.Favorites.route)},
        )

        NavigationBarItem(
            icon = {Icon(Icons.Outlined.Build, contentDescription = null)},
            label = {Text("Books")},
            selected = navController.currentDestination?.route == LibraryScreens.Books.route,
            onClick = {navController.navigate(LibraryScreens.Books.route)},
        )

        NavigationBarItem(
            icon = {Icon(Icons.Outlined.AccountCircle, contentDescription = null)},
            label = {Text("Profile")},
            selected = navController.currentDestination?.route == LibraryScreens.Profile.route,
            onClick = {navController.navigate(LibraryScreens.Profile.route)},
        )
        NavigationBarItem(
            icon = {Icon(Icons.Outlined.Settings, contentDescription = null)},
            label = {Text("Settings")},
            selected = navController.currentDestination?.route == LibraryScreens.Profile.route,
            onClick = {navController.navigateToSettingsGraph()},
        )
    }
}


@Composable
fun FavoritesScreen(navController : NavHostController) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier,
    ) {

        Text(text = "FavoritesScreen")
        Button(onClick = {navController.navigate(LibraryScreens.Profile.route)}) {
            Text(text = "Go to Profile")
        }
    }
}

@Composable
fun ProfileScreen(navController : NavHostController, onNavigateToLoginPage : () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier,
    ) {

        Text(text = "ProfileScreen")
        Button(onClick = onNavigateToLoginPage) {
            Text(text = "Login")
        }
    }

}

@Composable
fun BooksScreen(navController : NavHostController) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier,
    ) {

        Text(text = "BooksScreen")
        Button(onClick = {navController.navigate(LibraryScreens.BookDetails.route)}) {
            Text(text = "Go to BookDetails")
        }
    }
}

@Composable
fun BookDetailsScreen(navController : NavHostController) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier,
    ) {

        Text(text = "BookDetailsScreen")
        Button(onClick = {navController.navigate(LibraryScreens.Favorites.route)}) {
            Text(text = "Go to Favorite")
        }
    }
}


sealed class LibraryScreens(val route : String) {
    object Library : LibraryScreens("Library")
    object Books : LibraryScreens("Books")
    object BookDetails : LibraryScreens("BookDetails")
    object Favorites : LibraryScreens("Favorites")
    object Profile : LibraryScreens("Profile")
}
