package com.alguien.dijochamba.features.home.domain.repositories

import com.alguien.dijochamba.models.Product

interface ProductRepository {

    suspend fun getAllProducts(): List<Product>

    suspend fun getProductById(id: Int): Product?

    suspend fun addFavorite(product: Product)

    suspend fun removeFavorite(product: Product)
}