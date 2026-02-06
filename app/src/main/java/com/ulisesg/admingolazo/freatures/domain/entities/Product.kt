package com.ulisesg.admingolazo.freatures.domain.entities

data class Product(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val precio: Int,
    val imagenes: List<Image>,
    val esDestacado: Boolean,
    val estaActivo: Boolean
)
