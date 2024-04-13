package almerti.egline.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class MainCoordinator(
    val viewModel: MainViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun doStuff() {
        // TODO Handle UI Action
    }
}


@Composable
fun rememberMainCoordinator(
    viewModel: MainViewModel = hiltViewModel()
): MainCoordinator {
    return remember(viewModel) {
        MainCoordinator(
            viewModel = viewModel
        )
    }
}