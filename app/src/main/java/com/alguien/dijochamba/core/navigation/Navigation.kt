package com.alguien.dijochamba.core.navigation

import LoginForm
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pe.edu.upc.easyshop.core.root.Main
import com.alguien.dijochamba.core.ui.theme.EasyShopTheme
import com.alguien.dijochamba.features.auth.presentation.regiserProfile.RegisterProfile
import com.alguien.dijochamba.features.auth.presentation.register.RegisterForm
import com.alguien.dijochamba.features.home.presentation.di.PresentationModule.getProductDetailViewModel
import com.alguien.dijochamba.features.home.presentation.productdetail.ProductDetail
import com.alguien.dijochamba.features.onboarding.presentation.Intro1
import com.alguien.dijochamba.features.onboarding.presentation.Intro2

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Route.Intro1.route) {
        composable(Route.Intro1.route) {
            Intro1(onContinue = { navController.navigate(Route.Intro2.route) })
        }

        composable(Route.Intro2.route) {
            Intro2(
                onContinue = { navController.navigate(Route.Login.route) },       // Botón Continue → Login
                onCreateAccount = { navController.navigate(Route.Register.route) } // Texto "I already had account" → Register
            )        }

        // Login
        composable(Route.Login.route) {
            LoginForm(
                onLogin = { navController.navigate(Route.Main.route) },
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
                onSaveProfile = { navController.navigate(Route.Main.route) },
                onSkip = { navController.navigate(Route.Main.route) }
            )
        }

        //Main
        composable(Route.Main.route) {
            Main { productId ->
                navController.navigate("${Route.ProductDetail.route}/$productId")
            }
        }

        composable(
            route = Route.ProductDetail.routeWithArgument,
            arguments = listOf(navArgument(Route.ProductDetail.argument) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt(Route.ProductDetail.argument)?.let { productId ->
                val productDetailViewModel = getProductDetailViewModel()
                productDetailViewModel.getProductById(productId)
                ProductDetail(productDetailViewModel)
            }
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
