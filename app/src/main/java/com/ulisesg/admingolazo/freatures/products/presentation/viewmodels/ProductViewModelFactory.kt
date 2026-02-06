package com.ulisesg.admingolazo.freatures.products.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ulisesg.admingolazo.freatures.products.domain.usecases.CreateProductUseCase
import com.ulisesg.admingolazo.freatures.products.domain.usecases.DeleteProductUseCase
import com.ulisesg.admingolazo.freatures.products.domain.usecases.GetProductUseCase
import com.ulisesg.admingolazo.freatures.products.domain.usecases.UpdateProductUseCase


class ProductViewModelFactory(
    private val getProductsUseCase: GetProductUseCase,
    private val createProductUseCase: CreateProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(
                getProductsUseCase,
                createProductUseCase,
                updateProductUseCase,
                deleteProductUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
