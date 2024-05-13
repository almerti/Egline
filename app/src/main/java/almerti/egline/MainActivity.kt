package almerti.egline


import almerti.egline.navigation.RootNavHost
import almerti.egline.ui.theme.EglineTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EglineTheme {
                RootNavHost()
            }
        }
    }
}
