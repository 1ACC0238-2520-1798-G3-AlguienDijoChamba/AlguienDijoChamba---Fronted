package com.alguien.dijochamba.features.home.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.alguien.dijochamba.features.home.domain.repositories.ProductRepository
import com.alguien.dijochamba.models.Product

class HomeViewModel(private val repository: ProductRepository): ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())

    val products: StateFlow<List<Product>> = _products

    fun getAllProducts() {
        viewModelScope.launch {
           _products.value = repository.getAllProducts()
        }
    }

    init {
        getAllProducts()
    }
}