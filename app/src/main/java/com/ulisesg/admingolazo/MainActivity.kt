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

    private lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instanciamos los módulos para la inyección de dependencias
        val appContainer = AppContainer()
        val productModule = ProductModule(appContainer)

        // Creamos el ViewModel usando su Factory
        productViewModel = ViewModelProvider(
            this,
            productModule.providerProductViewModelFactory()
        )[ProductViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            AdminGolazoTheme {
                ProductScreen(viewModel = productViewModel)
            }
        }
    }
}
