package com.alguien.dijochamba.features.profile.presentation.di

import com.alguien.dijochamba.features.profile.presentation.ProfileViewModel

object ProfileModule {
    fun getProfileViewModel(): ProfileViewModel {
        return ProfileViewModel()
    }
}