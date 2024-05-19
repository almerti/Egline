package almerti.egline.feature.profile.edit

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal const val PROFILE_EDIT_ROUTE = "profile_edit"

internal fun NavController.navigateToEditProfile() {
    navigate(PROFILE_EDIT_ROUTE)
}

internal fun NavGraphBuilder.profileEditScreen(
    onNavigateToBack: () -> Unit
) {
    composable(PROFILE_EDIT_ROUTE) {
        val viewModel: EditProfileViewModel = hiltViewModel<EditProfileViewModel>()
        EditProfileScreen(
            viewModel = viewModel,
            onNavigateToBack = onNavigateToBack,
        )
    }
}
