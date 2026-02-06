package com.ulisesg.admingolazo.freatures.products.presentation.screens

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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ulisesg.admingolazo.freatures.products.domain.entities.Product
import com.ulisesg.admingolazo.freatures.products.presentation.components.ProductCard
import com.ulisesg.admingolazo.freatures.products.presentation.viewmodels.ProductUiState
import com.ulisesg.admingolazo.freatures.products.presentation.viewmodels.ProductViewModel

@Composable
fun ProductScreen(
    factory: ViewModelProvider.Factory
) {
    val viewModel: ProductViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsState()
    
    ProductScreenContent(
        uiState = uiState,
        onRefresh = { viewModel.getProducts() },
        onAddProduct = { viewModel.addProduct(it) },
        onUpdateProduct = { viewModel.updateProduct(it) },
        onDeleteProduct = { viewModel.removeProduct(it.id) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreenContent(
    uiState: ProductUiState,
    onRefresh: () -> Unit,
    onAddProduct: (Product) -> Unit,
    onUpdateProduct: (Product) -> Unit,
    onDeleteProduct: (Product) -> Unit
) {
    var showForm by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }

    if (showForm) {
        ProductFormScreen(
            product = selectedProduct,
            onSave = { product ->
                if (selectedProduct == null) onAddProduct(product)
                else onUpdateProduct(product)
                showForm = false
                selectedProduct = null
            },
            onDelete = { product ->
                onDeleteProduct(product)
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
                        IconButton(onClick = onRefresh) {
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
                        Button(onClick = onRefresh) {
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

