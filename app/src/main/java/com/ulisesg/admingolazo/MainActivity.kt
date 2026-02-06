package com.ulisesg.admingolazo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ulisesg.admingolazo.core.di.AppContainer
import com.ulisesg.admingolazo.freatures.auth.di.AuthModule
import com.ulisesg.admingolazo.freatures.auth.presentation.screen.LoginScreen
import com.ulisesg.admingolazo.freatures.auth.presentation.screen.RegisterScreen
import com.ulisesg.admingolazo.freatures.products.di.ProductModule
import com.ulisesg.admingolazo.freatures.products.presentation.screens.ProductScreen
import com.ulisesg.admingolazo.ui.theme.AdminGolazoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = AppContainer()
        val productModule = ProductModule(appContainer)
        val authModule = AuthModule(appContainer)

        enableEdgeToEdge()
        setContent {
            AdminGolazoTheme {
                MainNavigation(productModule, authModule)
            }
        }
    }
}

@Composable
fun MainNavigation(productModule: ProductModule, authModule: AuthModule) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                factory = authModule.provideAuthViewModelFactory(),
                onLoginSuccess = {
                    navController.navigate("products") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }
        composable("register") {
            RegisterScreen(
                factory = authModule.provideAuthViewModelFactory(),
                onRegisterSuccess = {
                    navController.navigate("products") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("products") {
            ProductScreen(
                factory = productModule.providerProductViewModelFactory()
            )
        }
    }
}
