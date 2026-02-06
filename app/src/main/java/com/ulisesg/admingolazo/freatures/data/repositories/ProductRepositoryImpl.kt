package com.ulisesg.admingolazo.freatures.data.repositories

import com.ulisesg.admingolazo.core.network.GolazoApi
import com.ulisesg.admingolazo.freatures.data.datasources.remote.mapper.toDomain
import com.ulisesg.admingolazo.freatures.domain.repositories.ProductRepository
import com.ulisesg.admingolazo.freatures.domain.entities.Product

class ProductRepositoryImpl(
    private val api: GolazoApi
): ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return try {
            val response = api.getProducts()
            response.map { it.toDomain() }
        } catch (e: Exception) {
            // Aquí podrías manejar diferentes tipos de errores (IO, HttpException, etc.)
            emptyList()
        }
    }
}
