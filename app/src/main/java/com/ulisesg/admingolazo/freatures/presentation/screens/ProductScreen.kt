package com.ulisesg.admingolazo.freatures.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ulisesg.admingolazo.freatures.domain.entities.Product
import com.ulisesg.admingolazo.freatures.presentation.components.ProductCard
import com.ulisesg.admingolazo.freatures.presentation.viewmodels.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    viewModel: ProductViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    
    var showForm by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    if (showForm) {
        ProductFormScreen(
            product = selectedProduct,
            onSave = { product ->
                if (selectedProduct == null) viewModel.addProduct(product)
                else viewModel.updateProduct(product)
                showForm = false
                selectedProduct = null
            },
            onDelete = { product ->
                viewModel.removeProduct(product.id)
                showForm = false
                selectedProduct = null
            },
            onBack = { 
                showForm = false
                selectedProduct = null
            }
        )
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Golazo Admin - Productos") },
                    actions = {
                        IconButton(onClick = { viewModel.getProducts() }) {
                            Icon(Icons.Default.Refresh, contentDescription = "Recargar")
                        }
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { 
                    selectedProduct = null
                    showForm = true 
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar Producto")
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else if (uiState.error != null) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Error: ${uiState.error}", color = MaterialTheme.colorScheme.error)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.getProducts() }) {
                            Text("Reintentar")
                        }
                    }
                } else if (uiState.products.isEmpty()) {
                    Text(
                        text = "No hay productos disponibles",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(uiState.products) { product ->
                            ProductCard(
                                product = product,
                                modifier = Modifier.clickable {
                                    selectedProduct = product
                                    showForm = true
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
