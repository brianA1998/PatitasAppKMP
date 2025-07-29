package com.patitasapp.kmp.navigation

sealed class NavigationRoute(val route: String){
    object Onboarding : NavigationRoute("onboarding")
    object Login : NavigationRoute("login")
}