package com.ulisesg.admingolazo.freatures.auth.domain.usecases

import com.ulisesg.admingolazo.freatures.auth.domain.entities.User
import com.ulisesg.admingolazo.freatures.auth.domain.repositories.AuthRepository

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return repository.login(email, password)
    }
}
