package com.ulisesg.admingolazo.core.di

import com.ulisesg.admingolazo.core.network.GolazoApi
import com.ulisesg.admingolazo.core.utils.Constants
import com.ulisesg.admingolazo.freatures.auth.data.repositories.AuthRepositoryImpl
import com.ulisesg.admingolazo.freatures.auth.domain.repositories.AuthRepository
import com.ulisesg.admingolazo.freatures.products.data.repositories.ProductRepositoryImpl
import com.ulisesg.admingolazo.freatures.products.domain.repositories.ProductRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val golazoApi: GolazoApi by lazy {
        retrofit.create(GolazoApi::class.java)
    }

    val productRepository: ProductRepository by lazy {
        ProductRepositoryImpl(golazoApi)
    }

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(golazoApi)
    }
}
