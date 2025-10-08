package com.alguien.dijochamba.core.navigation

import LoginForm
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alguien.dijochamba.core.ui.theme.EasyShopTheme
import com.alguien.dijochamba.features.auth.presentation.regiserProfile.RegisterProfile
import com.alguien.dijochamba.features.auth.presentation.register.RegisterForm
import com.alguien.dijochamba.features.onboarding.presentation.Intro1
import com.alguien.dijochamba.features.onboarding.presentation.Intro2
import com.alguien.dijochamba.features.profile.presentation.ProfileScreen
import com.alguien.dijochamba.features.profile.presentation.di.ProfileModule

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Route.Intro1.route) {
        composable(Route.Intro1.route) {
            Intro1(onContinue = { navController.navigate(Route.Intro2.route) })
        }

        composable(Route.Intro2.route) {
            Intro2(
                onContinue = { navController.navigate(Route.Login.route) },
                onCreateAccount = { navController.navigate(Route.Register.route) }
            )
        }

        // Login
        composable(Route.Login.route) {
            LoginForm(
                onLogin = { navController.navigate(Route.Profile.route) }, // Cambiado a Profile
                onCreateAccount = { navController.navigate(Route.Register.route) }
            )
        }

        // Register
        composable(Route.Register.route) {
            RegisterForm(
                onRegisterProfile = { userName ->
                    navController.navigate("register_profile/$userName")
                }
            )
        }

        // Register Profile
        composable(
            route = Route.RegisterProfile.routeWithArgument,
            arguments = listOf(navArgument("userName") { type = NavType.StringType })
        ) { backStackEntry ->
            val userName = backStackEntry.arguments?.getString("userName") ?: ""
            RegisterProfile(
                userName = userName,
                onBack = { navController.popBackStack() },
                onSaveProfile = {
                    navController.navigate(Route.Profile.route) {
                        popUpTo(0) // Limpia todo el back stack
                    }
                },
                onSkip = {
                    navController.navigate(Route.Profile.route) {
                        popUpTo(0) // Limpia todo el back stack
                    }
                }
            )
        }

        // Profile (Pantalla principal)
        composable(Route.Profile.route) {
            ProfileScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Preview
@Composable
fun NavigationPreview() {
    EasyShopTheme {
        Navigation()
    }
}