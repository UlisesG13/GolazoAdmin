package com.ulisesg.admingolazo.freatures.auth.data.repositories

import com.ulisesg.admingolazo.core.network.GolazoApi
import com.ulisesg.admingolazo.freatures.auth.data.remote.model.LoginRequest
import com.ulisesg.admingolazo.freatures.auth.data.remote.model.RegisterRequest
import com.ulisesg.admingolazo.freatures.auth.domain.entities.User
import com.ulisesg.admingolazo.freatures.auth.domain.repositories.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    private val api: GolazoApi
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<User> = withContext(Dispatchers.IO) {
        runCatching {
            val response = api.login(LoginRequest(email, password))

            User(
                id = response.usuario_id,
                nombre = response.email,
                email = response.email,
                token = response.token
            )
        }
    }

    override suspend fun register(nombre: String, email: String, password: String): Result<User> = withContext(Dispatchers.IO) {
        runCatching {
            val response = api.register(RegisterRequest(nombre, email, password))

            User(
                id = response.usuario_id,
                nombre = response.nombre,
                email = response.email,
                token = ""
            )
        }
    }
}
