package com.ulisesg.admingolazo.freatures.data.repositories

import com.ulisesg.admingolazo.core.network.GolazoApi
import com.ulisesg.admingolazo.freatures.data.datasources.remote.mapper.toDomain
import com.ulisesg.admingolazo.freatures.data.datasources.remote.mapper.toResponse
import com.ulisesg.admingolazo.freatures.domain.repositories.ProductRepository
import com.ulisesg.admingolazo.freatures.domain.entities.Product

class ProductRepositoryImpl(
    private val api: GolazoApi
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return try {
            val response = api.getProducts()
            response.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun createProduct(product: Product): Result<Product> = runCatching {
        val response = api.crearProducto(product.toResponse())
        response.toDomain()
    }

    override suspend fun updateProduct(product: Product): Result<Product> = runCatching {
        val response = api.actualizarProducto(product.id, product.toResponse())
        response.toDomain()
    }

    override suspend fun deleteProduct(id: String): Result<Unit> = runCatching {
        api.eliminarProducto(id)
    }
}
