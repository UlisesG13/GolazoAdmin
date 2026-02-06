package com.ulisesg.admingolazo.freatures.auth.domain.entities

data class User(
    val id: String,
    val nombre: String,
    val email: String,
    val token: String
)
