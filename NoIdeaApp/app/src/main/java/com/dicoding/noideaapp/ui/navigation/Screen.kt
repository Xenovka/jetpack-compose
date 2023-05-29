package com.dicoding.noideaapp.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Profile: Screen("profile")
    object DetailRanger : Screen("home/{rangerId}") {
        fun createRoute(rangerId: String) = "home/$rangerId"
    }
}