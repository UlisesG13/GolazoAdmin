package com.ulisesg.admingolazo.freatures.products.presentation.screens

import com.ulisesg.admingolazo.freatures.products.domain.entities.Product

data class ProductUiState(
    val isLoading: Boolean = false,
    val error: String? = null
)