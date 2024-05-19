package almerti.egline.navigation.navbar

import almerti.egline.feature.favorite.favoriteGraph
import almerti.egline.feature.profile.profileGraph
import almerti.egline.feature.settings.settingsGraph
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onNavigateToLoginPage: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {BottomNavBar(navController)},
    ) {
        NavHost(
            modifier = Modifier.windowInsetsPadding(WindowInsets.waterfall),
            navController = navController,
            startDestination = CATALOG_BROWSER_GRAPH_ROUTE,
        ) {

            favoriteGraph()

            composable(SAVED_GRAPH_ROUTE) {
                SavedScreen(navController)
            }
            composable(CATALOG_BROWSER_GRAPH_ROUTE) {
                FavoritesScreen(navController)
            }
            profileGraph(
                navController = navController,
                onNavigateToLoginPage = onNavigateToLoginPage,
            )
            settingsGraph()
        }

    }
}


@Composable
fun FavoritesScreen(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier,
    ) {

        Text(text = "FavoritesScreen")
    }
}

@Composable
fun BooksScreen(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier,
    ) {

        Text(text = "BooksScreen")
    }
}

@Composable
fun SavedScreen(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier,
    ) {

        Text(text = "SavedScreen")
    }
}

@Preview
@Composable
private fun MenuNavHostPreview() {
    MenuNavHost(
        navController = NavHostController(LocalContext.current),
        modifier = Modifier,
        onNavigateToLoginPage = {},
    )
}

