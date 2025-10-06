package com.alguien.dijochamba
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alguien.dijochamba.presentation.screens.welcome.WelcomeScreen
import com.alguien.dijochamba.presentation.theme.DijoChambaTheme
import com.alguien.dijochamba.presentation.screens.ready.ReadyScreen
import com.alguien.dijochamba.presentation.screens.register.RegisterScreen // Agregar este import

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DijoChambaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "welcome"
                    ) {
                        composable("welcome") {
                            WelcomeScreen(navController = navController)
                        }
                        composable("ready") {
                            ReadyScreen(navController = navController)
                        }
                        composable("register") {
                            RegisterScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}