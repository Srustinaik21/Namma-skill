package com.nammaskill.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nammaskill.ui.auth.LoginScreen
import com.nammaskill.ui.auth.LoginViewModel
import com.nammaskill.ui.home.HomeScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
}

@Composable
fun NavGraph(navController: NavHostController) {
    val authViewModel: LoginViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = authViewModel,
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(viewModel = authViewModel)
        }
    }
}
