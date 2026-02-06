package com.ulisesg.admingolazo.freatures.products.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulisesg.admingolazo.freatures.products.domain.entities.Product
import com.ulisesg.admingolazo.freatures.products.domain.usecases.CreateProductUseCase
import com.ulisesg.admingolazo.freatures.products.domain.usecases.DeleteProductUseCase
import com.ulisesg.admingolazo.freatures.products.domain.usecases.GetProductUseCase
import com.ulisesg.admingolazo.freatures.products.domain.usecases.UpdateProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProductUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)

class ProductViewModel(
    private val getProductsUseCase: GetProductUseCase,
    private val createProductUseCase: CreateProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val products = getProductsUseCase()
                _uiState.update { it.copy(isLoading = false, products = products) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message ?: "Error desconocido") }
            }
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            createProductUseCase(product)
                .onSuccess { getProducts() }
                .onFailure { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message) }
                }
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            updateProductUseCase(product)
                .onSuccess { getProducts() }
                .onFailure { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message) }
                }
        }
    }

    fun removeProduct(id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            deleteProductUseCase(id)
                .onSuccess { getProducts() }
                .onFailure { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message) }
                }
        }
    }
}
