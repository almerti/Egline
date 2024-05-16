package almerti.egline.feature.register

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val REGISTER_ROUTE = "register"

internal fun NavGraphBuilder.registerScreen(
    onBackClick: () -> Unit,
    onNavigateToMainPage: () -> Unit
) {
    composable(REGISTER_ROUTE) {
        val viewModel: RegisterViewModel = hiltViewModel<RegisterViewModel>()
        RegisterScreen(
            viewModel = viewModel,
            onBackClick = onBackClick,
            onNavigateToMainPage = onNavigateToMainPage,
        )
    }
}
