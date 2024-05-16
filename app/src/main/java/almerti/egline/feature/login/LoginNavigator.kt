package almerti.egline.feature.login

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val LOGIN_ROUTE = "login"

internal fun NavGraphBuilder.loginScreen(
    onBackClick: () -> Unit,
    onNavigateToRegisterGraph: () -> Unit,
    onNavigateToMainPage: () -> Unit
) {
    composable(LOGIN_ROUTE) {
        val viewModel: LoginViewModel = hiltViewModel<LoginViewModel>()
        LoginScreen(
            viewModel = viewModel,
            onBackClick = onBackClick,
            onNavigateToRegisterGraph = onNavigateToRegisterGraph,
            onNavigateToMainPage = onNavigateToMainPage,
        )
    }
}
