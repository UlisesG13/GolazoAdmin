package com.ulisesg.admingolazo.freatures.auth.di

import com.ulisesg.admingolazo.core.di.AppContainer
import com.ulisesg.admingolazo.freatures.auth.domain.usecases.LoginUseCase
import com.ulisesg.admingolazo.freatures.auth.domain.usecases.RegisterUseCase
import com.ulisesg.admingolazo.freatures.auth.presentation.viewmodels.AuthViewModelFactory

class AuthModule(
    private val appContainer: AppContainer
) {
    private fun provideLoginUseCase() = LoginUseCase(appContainer.authRepository)
    private fun provideRegisterUseCase() = RegisterUseCase(appContainer.authRepository)

    fun provideAuthViewModelFactory(): AuthViewModelFactory {
        return AuthViewModelFactory(
            loginUseCase = provideLoginUseCase(),
            registerUseCase = provideRegisterUseCase()
        )
    }
}
