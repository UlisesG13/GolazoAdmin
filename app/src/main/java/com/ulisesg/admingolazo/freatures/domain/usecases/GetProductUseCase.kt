package com.ulisesg.admingolazo.freatures.domain.usecases

import com.ulisesg.admingolazo.freatures.domain.entities.Product
import com.ulisesg.admingolazo.freatures.domain.repositories.ProductRepository

data class GetProductUseCase(
    private val repository: ProductRepository
){
    suspend operator fun invoke(): List<Product> {
        return try {
            repository.getProducts()
        } catch (e: Exception) {
            emptyList()
        }

    }

}
