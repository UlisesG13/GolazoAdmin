package com.ulisesg.admingolazo.core.network

import com.ulisesg.admingolazo.freatures.data.datasources.remote.model.ProductResponse
import retrofit2.http.GET

interface GolazoApi {
    // Hemos añadido "api/" al inicio para coincidir con tu ruta completa
    @GET("api/productos/")
    suspend fun getProducts(): List<ProductResponse>
}
