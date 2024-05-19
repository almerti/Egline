package almerti.egline.feature.profile.main

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val PROFILE_MAIN_ROUTE = "profile_main"

internal fun NavController.navigateToMainProfile() {
    navigate(PROFILE_MAIN_ROUTE)
}

internal fun NavGraphBuilder.profileMainScreen(
    onNavigateToLoginPage: () -> Unit,
    onNavigateToEditPage: () -> Unit
) {
    composable(PROFILE_MAIN_ROUTE) {
        val viewModel: ProfileMainViewModel = hiltViewModel<ProfileMainViewModel>()
        ProfileMainScreen(
            viewModel = viewModel,
            onNavigateToLoginPage = onNavigateToLoginPage,
            onNavigateToEditPage = onNavigateToEditPage,
        )
    }
}
