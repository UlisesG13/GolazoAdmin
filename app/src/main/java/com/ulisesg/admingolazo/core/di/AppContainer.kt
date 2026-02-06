package com.ulisesg.admingolazo.core.di

import com.ulisesg.admingolazo.core.network.GolazoApi
import com.ulisesg.admingolazo.core.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.ulisesg.admingolazo.freatures.data.repositories.ProductRepositoryImpl

class AppContainer{
    private val golazoApiRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val golazoApi : GolazoApi by lazy {
        golazoApiRetrofit.create(GolazoApi::class.java)
    }

    val productRepository: ProductRepositoryImpl by lazy {
        ProductRepositoryImpl(golazoApi)
    }
}
