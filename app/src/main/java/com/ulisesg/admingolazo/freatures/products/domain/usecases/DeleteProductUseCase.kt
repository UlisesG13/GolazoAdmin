package com.ulisesg.admingolazo.freatures.products.domain.usecases

import com.ulisesg.admingolazo.freatures.products.domain.repositories.ProductRepository


class DeleteProductUseCase(
    private val repository: ProductRepository
){
    suspend operator fun invoke(id: String): Result<Unit> {
        return repository.deleteProduct(id)
    }
}
