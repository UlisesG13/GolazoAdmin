package com.ulisesg.admingolazo.freatures.di

import com.ulisesg.admingolazo.core.di.AppContainer
import com.ulisesg.admingolazo.freatures.domain.usecases.CreateProductUseCase
import com.ulisesg.admingolazo.freatures.domain.usecases.DeleteProductUseCase
import com.ulisesg.admingolazo.freatures.domain.usecases.GetProductUseCase
import com.ulisesg.admingolazo.freatures.domain.usecases.UpdateProductUseCase
import com.ulisesg.admingolazo.freatures.presentation.viewmodels.ProductViewModelFactory

class ProductModule(
    private val appContainer: AppContainer
) {
    private fun provideGetProductsUseCase(): GetProductUseCase {
        return GetProductUseCase(appContainer.productRepository)
    }

    private fun provideCreateProductsUseCase(): CreateProductUseCase {
        return CreateProductUseCase(appContainer.productRepository)
    }

    private fun provideUpdateProductsUseCase(): UpdateProductUseCase {
        return UpdateProductUseCase(appContainer.productRepository)
    }

    private fun provideDeleteProductsUseCase(): DeleteProductUseCase {
        return DeleteProductUseCase(appContainer.productRepository)
    }

    fun providerProductViewModelFactory(): ProductViewModelFactory {
        return ProductViewModelFactory(
            getProductsUseCase = provideGetProductsUseCase(),
            createProductUseCase = provideCreateProductsUseCase(),
            updateProductUseCase = provideUpdateProductsUseCase(),
            deleteProductUseCase = provideDeleteProductsUseCase()
        )
    }
}
