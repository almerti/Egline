package almerti.egline.navigation.navbar

import almerti.egline.R
import almerti.egline.feature.catalog.CATALOG_GRAPH_ROUTE
import almerti.egline.feature.favorite.FAVORITE_GRAPH_ROUTE
import almerti.egline.feature.profile.PROFILE_GRAPH_ROUTE
import almerti.egline.feature.saved.SAVED_GRAPH_ROUTE
import almerti.egline.feature.settings.SETTINGS_GRAPH_ROUTE
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material.icons.filled.FolderSpecial
import androidx.compose.material.icons.filled.LocalLibrary
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.FolderSpecial
import androidx.compose.material.icons.outlined.LocalLibrary
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavBar(navController : NavHostController) {
    val state = rememberNavigationBarState(navController)
    NavigationBar {
        val isFavoritesSelected by state.isRouteSelected(FAVORITE_GRAPH_ROUTE)
            .collectAsState(initial = false)

        val favoritesIcon =
            if (isFavoritesSelected) Icons.Filled.FolderSpecial else Icons.Outlined.FolderSpecial
        NavigationBarItem(

            icon = {
                AnimatedIcon(targetIcon = favoritesIcon, contentDescription = null)
            },
            label = {Text(stringResource(id = R.string.favorite_header))},
            selected = isFavoritesSelected,
            onClick = {state.openRoute(FAVORITE_GRAPH_ROUTE)},
        )

        val isBrowserSelected by state.isRouteSelected(CATALOG_GRAPH_ROUTE)
            .collectAsState(initial = false)

        val browserIcon =
            if (isBrowserSelected) Icons.Filled.CollectionsBookmark else Icons.Outlined.CollectionsBookmark

        NavigationBarItem(
            icon = {AnimatedIcon(browserIcon, contentDescription = null)},
            label = {Text(stringResource(id = R.string.navbar_library))},
            selected = isBrowserSelected,
            onClick = {state.openRoute(CATALOG_GRAPH_ROUTE)},
        )

        val isSavedSelected by state.isRouteSelected(SAVED_GRAPH_ROUTE)
            .collectAsState(initial = false)

        val savedIcon =
            if (isSavedSelected) Icons.Filled.LocalLibrary else Icons.Outlined.LocalLibrary
        NavigationBarItem(
            icon = {AnimatedIcon(savedIcon, contentDescription = null)},
            label = {Text(stringResource(id = R.string.navbar_local))},
            selected = isSavedSelected,
            onClick = {state.openRoute(SAVED_GRAPH_ROUTE)},
        )

        val isProfileSelected by state.isRouteSelected(PROFILE_GRAPH_ROUTE)
            .collectAsState(initial = false)

        val profileIcon =
            if (isProfileSelected) Icons.Filled.AccountCircle else Icons.Outlined.AccountCircle
        NavigationBarItem(
            icon = {AnimatedIcon(profileIcon, contentDescription = null)},
            label = {Text(stringResource(id = R.string.navbar_profile))},
            selected = isProfileSelected,
            onClick = {state.openRoute(PROFILE_GRAPH_ROUTE)},
        )

        val isSettingsSelected by state.isRouteSelected(SETTINGS_GRAPH_ROUTE)
            .collectAsState(initial = false)

        val settingsIcon =
            if (isSettingsSelected) Icons.Filled.Settings else Icons.Outlined.Settings
        NavigationBarItem(
            icon = {AnimatedIcon(settingsIcon, contentDescription = null)},
            label = {Text(stringResource(id = R.string.navbar_settings))},
            selected = isSettingsSelected,
            onClick = {state.openRoute(SETTINGS_GRAPH_ROUTE)},
        )
    }
}

@Composable
fun AnimatedIcon(
    targetIcon : ImageVector,
    contentDescription : String?
) {
    AnimatedContent(
        targetState = targetIcon,
        transitionSpec = {
            fadeIn(animationSpec = tween(durationMillis = 600)) togetherWith
                fadeOut(animationSpec = tween(durationMillis = 500))
        },
        label = "IconChangeAnimation",
    ) {icon ->
        Icon(imageVector = icon, contentDescription = contentDescription)
    }
}

@Preview
@Composable
private fun NavigationBarPreview() {
    MaterialTheme {
        BottomNavBar(rememberNavController())
    }
}
