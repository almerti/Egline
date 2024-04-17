package almerti.egline


import almerti.egline.navigation.FullscreenNavigationGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import almerti.egline.ui.theme.EglineTheme
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EglineTheme {
                val navController = rememberNavController()

                FullscreenNavigationGraph(navController)
            }
        }
    }
}