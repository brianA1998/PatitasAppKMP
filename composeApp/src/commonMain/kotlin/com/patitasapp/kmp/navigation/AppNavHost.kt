package com.patitasapp.kmp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.patitasapp.kmp.authentication.presentation.login.LoginScreen
import com.patitasapp.kmp.authentication.presentation.login.LoginViewModel
import com.patitasapp.kmp.home.presentation.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen()
        }

    }
}