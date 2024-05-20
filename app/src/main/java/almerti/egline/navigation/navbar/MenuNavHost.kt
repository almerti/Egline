package almerti.egline.navigation.navbar

import almerti.egline.feature.catalog.catalogGraph
import almerti.egline.feature.favorite.favoriteGraph
import almerti.egline.feature.profile.profileGraph
import almerti.egline.feature.saved.SAVED_GRAPH_ROUTE
import almerti.egline.feature.saved.savedGraph
import almerti.egline.feature.settings.settingsGraph
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
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


            savedGraph(onNavigateToBook = onNavigateToBookPage)

            catalogGraph(
                onNavigateToBook = onNavigateToBookPage,
            )

            profileGraph(
                navController = navController,
                onNavigateToLoginPage = onNavigateToLoginPage,
            )
            settingsGraph()

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

