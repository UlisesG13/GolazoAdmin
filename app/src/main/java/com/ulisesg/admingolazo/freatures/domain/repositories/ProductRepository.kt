package com.ulisesg.admingolazo.freatures.domain.repositories

import com.ulisesg.admingolazo.freatures.domain.entities.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}

