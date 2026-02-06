package com.ulisesg.admingolazo.freatures.domain.repositories

import com.ulisesg.admingolazo.freatures.domain.entities.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun createProduct(product: Product): Result<Product>
    suspend fun updateProduct(product: Product): Result<Product>
    suspend fun deleteProduct(id: String): Result<Unit>
}


