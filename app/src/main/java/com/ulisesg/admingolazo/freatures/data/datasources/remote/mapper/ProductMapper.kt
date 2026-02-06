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
        estaActivo = esta_activo,
        fecha_creacion = fecha_creacion,
        categoria_id = categoria_id
    )
}

fun Product.toResponse(): ProductResponse {
    return ProductResponse(
        producto_id = if (id.isEmpty()) null else id,
        nombre = nombre,
        precio = precio,
        descripcion = descripcion,
        esta_activo = estaActivo,
        esta_destacado = esDestacado,
        categoria_id = categoria_id,
        fecha_creacion = fecha_creacion,
        imagenes = imagenes.map { ImageReponse(it.id, it.path, it.orden) }
    )
}

fun List<ProductResponse>.toDomain(): List<Product> {
    return this.map { it.toDomain() }
}
