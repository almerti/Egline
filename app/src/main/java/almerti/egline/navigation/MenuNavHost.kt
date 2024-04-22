package almerti.egline.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation

@Composable
fun MenuNavHost(
    navController : NavHostController = rememberNavController() ,
    modifier : Modifier = Modifier
) {
    Scaffold { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding) ,
            navController = navController ,
            startDestination = LibraryScreens.Favorites.route
        ) {
            navigation(
                route = LibraryScreens.Library.route ,
                startDestination = LibraryScreens.Books.route
            ) {
                composable(LibraryScreens.Books.route) {
                    BooksScreen(navController)
                }
                composable(
                    route = LibraryScreens.BookDetails.route
                ) { backStackEntry ->
                    BookDetailsScreen(navController)
                }
            }

            composable(LibraryScreens.Favorites.route) {
                FavoritesScreen(navController)
            }

            composable(LibraryScreens.Profile.route) {
                ProfileScreen(navController)
            }
        }
    }
}


@Composable
fun FavoritesScreen(navController : NavHostController) {
    Column(
        verticalArrangement = Arrangement.Top ,
        modifier = Modifier
    ) {

        Text(text = "FavoritesScreen")
        Button(onClick = { navController.navigate(LibraryScreens.Profile.route) }) {
            Text(text = "Go to Profile")
        }
    }
}

@Composable
fun ProfileScreen(navController : NavHostController) {
    Column(
        verticalArrangement = Arrangement.Top ,
        modifier = Modifier
    ) {

        Text(text = "ProfileScreen")
        Button(onClick = { navController.navigate(LibraryScreens.Books.route) }) {
            Text(text = "Go to Books")
        }
    }

}

@Composable
fun BooksScreen(navController : NavHostController) {
    Column(
        verticalArrangement = Arrangement.Top ,
        modifier = Modifier
    ) {

        Text(text = "BooksScreen")
        Button(onClick = { navController.navigate(LibraryScreens.BookDetails.route) }) {
            Text(text = "Go to BookDetails")
        }
    }
}

@Composable
fun BookDetailsScreen(navController : NavHostController) {
    Column(
        verticalArrangement = Arrangement.Top ,
        modifier = Modifier
    ) {

        Text(text = "BookDetailsScreen")
        Button(onClick = { navController.navigate(LibraryScreens.Favorites.route) }) {
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