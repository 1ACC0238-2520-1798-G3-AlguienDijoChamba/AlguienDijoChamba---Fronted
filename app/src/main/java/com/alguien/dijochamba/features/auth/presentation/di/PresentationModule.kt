package com.alguien.dijochamba.features.auth.presentation.di

import com.alguien.dijochamba.features.auth.data.di.DataModule.getAuthRepository
import com.alguien.dijochamba.features.auth.presentation.login.LoginViewModel

object PresentationModule {

    fun getLoginViewModel(): LoginViewModel {
        return LoginViewModel(getAuthRepository())
    }
}