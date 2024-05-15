package almerti.egline.feature.register

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation

const val REGISTER_GRAPH_ROUTE = "login_route"

fun NavController.navigateToRegisterGraph() {
    navigate(REGISTER_GRAPH_ROUTE)
}

fun NavGraphBuilder.registerGraph() {
    navigation(
        route = REGISTER_GRAPH_ROUTE,
        startDestination = REGISTER_ROUTE,
    ) {
        registerScreen()
    }
}
