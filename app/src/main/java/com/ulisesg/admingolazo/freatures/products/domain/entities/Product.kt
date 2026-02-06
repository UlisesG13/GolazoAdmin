package com.ulisesg.admingolazo.freatures.products.domain.entities

data class Product(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val precio: Int,
    val imagenes: List<Image>,
    val esDestacado: Boolean,
    val estaActivo: Boolean,
    val fecha_creacion: String,
    val categoria_id: Int
)
