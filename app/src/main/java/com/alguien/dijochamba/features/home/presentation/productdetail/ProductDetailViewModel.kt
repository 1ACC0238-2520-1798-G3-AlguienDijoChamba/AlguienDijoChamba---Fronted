package com.alguien.dijochamba.features.home.presentation.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.alguien.dijochamba.features.home.domain.repositories.ProductRepository
import com.alguien.dijochamba.models.Product

class ProductDetailViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _product = MutableStateFlow<Product?>(null)
    val product: StateFlow<Product?> = _product

    fun getProductById(id: Int) {
        viewModelScope.launch {
            _product.value = repository.getProductById(id)
        }
    }

    fun toggleFavorite() {
        _product.value?.let { product ->
            viewModelScope.launch {
                if (product.isFavorite) {
                    repository.removeFavorite(product)
                } else {
                    repository.addFavorite(product)
                }
                _product.value = product.copy(
                    isFavorite = !product.isFavorite
                )
            }
        }

    }
}