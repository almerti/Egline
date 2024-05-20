package almerti.egline.navigation.navbar

import almerti.egline.feature.catalog.CATALOG_GRAPH_ROUTE
import almerti.egline.feature.favorite.FAVORITE_GRAPH_ROUTE
import almerti.egline.feature.profile.PROFILE_GRAPH_ROUTE
import almerti.egline.feature.saved.SAVED_GRAPH_ROUTE
import almerti.egline.feature.settings.SETTINGS_GRAPH_ROUTE
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


@Stable
internal class NavigationBarState(private val navController : NavController) {
    private val navigationBarRoutes = listOf(
        FAVORITE_GRAPH_ROUTE,
        CATALOG_GRAPH_ROUTE,
        SAVED_GRAPH_ROUTE,
        PROFILE_GRAPH_ROUTE,
        SETTINGS_GRAPH_ROUTE,
    )


    @SuppressLint("RestrictedApi")
    fun isRouteSelected(route : String) : Flow<Boolean> {
        return navController.currentBackStack.map {backStack ->
            backStack
                .map {it.destination.route}
                .lastOrNull {navigationBarRoutes.contains(it)}
                .let {it == route}
        }
    }


    fun openRoute(route : String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
internal fun rememberNavigationBarState(navController : NavController) : NavigationBarState {
    return remember(navController) {
        NavigationBarState(navController)
    }
}
