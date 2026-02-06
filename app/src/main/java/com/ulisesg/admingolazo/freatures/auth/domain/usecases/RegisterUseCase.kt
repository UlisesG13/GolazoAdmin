package com.ulisesg.admingolazo.freatures.auth.domain.usecases

import com.ulisesg.admingolazo.freatures.auth.domain.entities.User
import com.ulisesg.admingolazo.freatures.auth.domain.repositories.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(nombre: String, email: String, password: String): Result<User> {
        return repository.register(nombre, email, password)
    }
}
