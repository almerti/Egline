package almerti.egline.navigation.navbar

import almerti.egline.feature.favorite.favoriteGraph
import almerti.egline.feature.profile.profileGraph
import almerti.egline.feature.settings.settingsGraph
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
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
    modifier : Modifier = Modifier,
    navController : NavHostController = rememberNavController(),
    onNavigateToLoginPage : () -> Unit,
    onNavigateToBookPage : (Int) -> Unit
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {BottomNavBar(navController)},
    ) {
        NavHost(
            modifier = Modifier.windowInsetsPadding(WindowInsets.waterfall),
            navController = navController,
            startDestination = SAVED_GRAPH_ROUTE,
        ) {

            favoriteGraph()

            composable(SAVED_GRAPH_ROUTE) {
                SavedScreen(
                    navigateToBookPage = {bookId ->
                        onNavigateToBookPage(bookId)
                    },
                )
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
fun FavoritesScreen(navController : NavHostController) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier,
    ) {

        Text(text = "FavoritesScreen")
    }
}

@Composable
fun SavedScreen(
    navigateToBookPage : (id : Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.safeContentPadding(),
    ) {

        Text(text = "SavedScreen")

        Button(onClick = {navigateToBookPage(1)}) {
            Text(text = "Go to Book")
        }
    }
}

@Preview
@Composable
private fun MenuNavHostPreview() {
    MenuNavHost(
        navController = NavHostController(LocalContext.current),
        modifier = Modifier,
        onNavigateToLoginPage = {},
        onNavigateToBookPage = {},
    )
}

