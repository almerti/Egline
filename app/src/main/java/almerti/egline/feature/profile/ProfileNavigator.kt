package almerti.egline.feature.profile

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val PROFILE_ROUTE = "profile"

internal fun NavGraphBuilder.profileScreen(
    onNavigateToLoginPage: () -> Unit
) {
    composable(PROFILE_ROUTE) {
        val viewModel: ProfileViewModel = hiltViewModel<ProfileViewModel>()
        ProfileScreen(
            viewModel = viewModel,
            onNavigateToLoginPage = onNavigateToLoginPage,
        )
    }
}
