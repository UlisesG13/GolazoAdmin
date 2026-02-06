package com.ulisesg.admingolazo.freatures.presentation.screens

import com.ulisesg.admingolazo.freatures.domain.entities.Product

data class ProductUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)