package com.ulisesg.admingolazo.freatures.auth.presentation.screen

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val successMessage: String? = null
)
