package org.example.product.repository

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.onSubscription
import org.example.product.model.Product

class MemoryProductRepository : ProductRepository {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    override val products = _products.asStateFlow()

    override suspend fun addProduct(product: Product) {
        _products.value += product
    }
}