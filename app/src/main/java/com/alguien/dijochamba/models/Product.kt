package com.alguien.dijochamba.models

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val image: String,
    var isFavorite: Boolean = false
)
