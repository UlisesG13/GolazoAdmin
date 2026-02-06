package com.ulisesg.admingolazo.freatures.auth.data.remote.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val nombre: String,
    val email: String,
    val password: String,
    val rol: String = "admin"
)

data class AuthResponse(
    val access_token: String,
    val token_type: String,
    val usuario: UserDto
)

data class UserDto(
    val usuario_id: String,
    val nombre: String,
    val email: String,
)
