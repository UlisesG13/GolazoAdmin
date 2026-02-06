package com.ulisesg.admingolazo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.ulisesg.admingolazo.core.di.AppContainer
import com.ulisesg.admingolazo.freatures.di.ProductModule
import com.ulisesg.admingolazo.freatures.presentation.screens.ProductScreen
import com.ulisesg.admingolazo.freatures.presentation.viewmodels.ProductViewModel
import com.ulisesg.admingolazo.ui.theme.AdminGolazoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializamos las dependencias dentro de onCreate para mantenerlo simple
        val appContainer = AppContainer()
        val productModule = ProductModule(appContainer)

        // Obtenemos el ViewModel usando su Factory centralizada que ya inyecta todos los Use Cases
        val productViewModel = ViewModelProvider(
            this,
            productModule.providerProductViewModelFactory()
        )[ProductViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            AdminGolazoTheme {
                // Mostramos la pantalla principal con el ViewModel configurado
                ProductScreen(viewModel = productViewModel)
            }
        }
    }
}
