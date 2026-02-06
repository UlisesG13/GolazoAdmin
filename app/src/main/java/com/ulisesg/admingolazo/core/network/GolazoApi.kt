package com.ulisesg.admingolazo.core.network

import com.ulisesg.admingolazo.freatures.data.datasources.remote.model.ImageReponse
import com.ulisesg.admingolazo.freatures.data.datasources.remote.model.ProductResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GolazoApi {
    @GET("api/productos/")
    suspend fun getProducts(): List<ProductResponse>

    @GET("api/imagenes/")
    suspend fun getImages(): List<ImageReponse>

    @POST("api/productos/")
    suspend fun crearProducto(@Body producto: ProductResponse): ProductResponse

    @PUT("api/productos/{id}/")
    suspend fun actualizarProducto(@Path("id") id: String, @Body producto: ProductResponse): ProductResponse

    @DELETE("api/productos/{id}/")
    suspend fun eliminarProducto(@Path("id") id: String)
}
