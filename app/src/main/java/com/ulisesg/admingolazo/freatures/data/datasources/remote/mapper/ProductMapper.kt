package com.ulisesg.admingolazo.freatures.data.datasources.remote.mapper

import com.ulisesg.admingolazo.freatures.data.datasources.remote.model.ImageReponse
import com.ulisesg.admingolazo.freatures.data.datasources.remote.model.ProductResponse
import com.ulisesg.admingolazo.freatures.domain.entities.Image
import com.ulisesg.admingolazo.freatures.domain.entities.Product

fun ProductResponse.toDomain(): Product {
    return Product(
        id = producto_id ?: "",
        nombre = nombre,
        descripcion = descripcion ?: "",
        precio = precio,
        imagenes = imagenes?.toDomain() ?: emptyList(),
        esDestacado = esta_destacado,
        estaActivo = esta_activo
    )
}

fun List<ProductResponse>.toDomain(): List<Product> {
    return this.map { it.toDomain() }
}