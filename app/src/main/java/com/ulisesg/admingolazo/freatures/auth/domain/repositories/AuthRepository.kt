package com.ulisesg.admingolazo.freatures.auth.domain.repositories

import com.ulisesg.admingolazo.freatures.auth.domain.entities.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(nombre: String, email: String, password: String): Result<User>
}
