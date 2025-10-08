package com.alguien.dijochamba.features.home.presentation.di

import com.alguien.dijochamba.features.home.data.di.DataModule.getProductRepository
import com.alguien.dijochamba.features.home.presentation.home.HomeViewModel
import com.alguien.dijochamba.features.home.presentation.productdetail.ProductDetailViewModel

object PresentationModule {
    fun getHomeViewModel(): HomeViewModel {
        return HomeViewModel(getProductRepository())
    }

    fun getProductDetailViewModel(): ProductDetailViewModel {
        return ProductDetailViewModel(getProductRepository())
    }
}