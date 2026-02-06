package com.ulisesg.admingolazo.freatures.auth.presentation.viewmodels

import com.ulisesg.admingolazo.freatures.auth.domain.entities.User

data class AuthUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)
