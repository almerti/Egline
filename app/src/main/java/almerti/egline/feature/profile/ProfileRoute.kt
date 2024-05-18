package almerti.egline.feature.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val PROFILE_GRAPH_ROUTE = "profile_route"

fun NavController.navigateToProfileGraph() {
    navigate(PROFILE_GRAPH_ROUTE)
}

fun NavGraphBuilder.profileGraph(
    onNavigateToLoginPage: () -> Unit
) {
    navigation(
        route = PROFILE_GRAPH_ROUTE,
        startDestination = PROFILE_ROUTE,
    ) {
        profileScreen(
            onNavigateToLoginPage = onNavigateToLoginPage,
        )
    }
}
