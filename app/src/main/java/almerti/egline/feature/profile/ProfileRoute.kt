package almerti.egline.feature.profile

import almerti.egline.feature.login.navigateToLoginGraph
import almerti.egline.feature.profile.edit.navigateToEditProfile
import almerti.egline.feature.profile.edit.profileEditScreen
import almerti.egline.feature.profile.main.PROFILE_MAIN_ROUTE
import almerti.egline.feature.profile.main.profileMainScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val PROFILE_GRAPH_ROUTE = "profile_route"

fun NavController.navigateToProfileGraph() {
    navigate(PROFILE_GRAPH_ROUTE)
}

fun NavGraphBuilder.profileGraph(
    navController: NavController
) {
    navigation(
        route = PROFILE_GRAPH_ROUTE,
        startDestination = PROFILE_MAIN_ROUTE,
    ) {
        profileMainScreen(
            onNavigateToLoginPage = {
                navController.navigateToLoginGraph()
            },
            onNavigateToEditPage = {
                navController.navigateToEditProfile()
            },
        )
        profileEditScreen(
            onNavigateToBack = {
                navController.popBackStack()
            },
        )
    }
}
