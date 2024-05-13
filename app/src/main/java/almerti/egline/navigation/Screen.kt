package almerti.egline.navigation

sealed class Screen(val route : String) {
    fun createRoute(bookId : String) : String {
        return "$route?bookId=$bookId"
    }

    object Main : Screen("main")
    object Catalog : Screen("catalog")
    object Notifications : Screen("notifications")
    object Settings : Screen("settings")
    object Profile : Screen("profile")
    object AboutUs : Screen("about_us")
    object BookInfo : Screen("book_info")
    object Reading : Screen("reading")
    object AudioPlayer : Screen("audio_player")
    object Login : Screen("login")
    object Register : Screen("register")
}
