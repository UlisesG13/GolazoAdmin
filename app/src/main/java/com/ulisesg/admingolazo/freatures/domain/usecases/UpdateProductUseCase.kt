package com.ulisesg.admingolazo.freatures.domain.usecases

import com.ulisesg.admingolazo.freatures.domain.entities.Product
import com.ulisesg.admingolazo.freatures.domain.repositories.ProductRepository

class UpdateProductUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: Product): Result<Product> {
        return repository.updateProduct(product)
    }
}
