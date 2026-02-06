package com.ulisesg.admingolazo.freatures.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ulisesg.admingolazo.freatures.domain.usecases.GetProductUseCase

class ProductViewModelFactory(
    private val getProductUseCase: GetProductUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
    @Suppress("UNCHECKED_CAST")
    return ProductViewModel(getProductUseCase) as T
    }
}

