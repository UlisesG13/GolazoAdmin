package com.ulisesg.admingolazo.freatures.auth.data.remote.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val nombre: String,
    val email: String,
    val password: String
)

data class LoginResponseDto(
    val token: String,
    val usuario_id: String,
    val email: String,
    val rol: String
)

data class UserResponse(
    val usuario_id: String,
    val nombre: String,
    val email: String,
    val telefono: String?,
    val rol: String?,
    val fecha_creacion: String,
    val is_authenticated: Boolean? = false,
    val google_id: String? = null,
    val password: String? = null,
    val fecha_eliminacion: String? = null
)