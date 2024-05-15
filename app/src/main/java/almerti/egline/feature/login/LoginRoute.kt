package almerti.egline.feature.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val LOGIN_GRAPH_ROUTE = "login_route"

fun NavController.navigateToLoginGraph() {
    navigate(LOGIN_GRAPH_ROUTE)
}

fun NavGraphBuilder.loginGraph(
    onBackClick: () -> Unit,
    onNavigateToRegisterGraph: () -> Unit
) {
    navigation(
        route = LOGIN_GRAPH_ROUTE,
        startDestination = LOGIN_ROUTE,
    ) {
        loginScreen(
            onBackClick = onBackClick,
            onNavigateToRegisterGraph = onNavigateToRegisterGraph,
        )
    }
}

