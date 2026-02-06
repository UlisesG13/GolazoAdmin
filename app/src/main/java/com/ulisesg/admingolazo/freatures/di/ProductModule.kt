package com.ulisesg.admingolazo.freatures.di

import com.ulisesg.admingolazo.core.di.AppContainer
import com.ulisesg.admingolazo.freatures.domain.usecases.GetProductUseCase
import com.ulisesg.admingolazo.freatures.presentation.viewmodels.ProductViewModelFactory

data class ProductModule(
    private val appContainer: AppContainer
){
    private fun provideGetProductsUseCase(): GetProductUseCase{
        return GetProductUseCase(appContainer.productRepository)
    }

    fun providerProductViewModelFactory(): ProductViewModelFactory {
        return ProductViewModelFactory(
            getProductUseCase = provideGetProductsUseCase(),
        )
    }


}
