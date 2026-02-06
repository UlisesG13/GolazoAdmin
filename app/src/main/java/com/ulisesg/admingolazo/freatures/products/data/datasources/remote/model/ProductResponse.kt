package com.ulisesg.admingolazo.freatures.products.data.datasources.remote.model

data class ProductResponse(
    val producto_id: String?,
    val nombre: String,
    val precio: Int,
    val descripcion: String?,
    val esta_activo: Boolean,
    val esta_destacado: Boolean,
    val categoria_id: Int,
    val fecha_creacion: String,
    val imagenes: List<ImageReponse>? = emptyList()
)
