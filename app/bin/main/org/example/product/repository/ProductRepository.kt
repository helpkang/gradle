package org.example.product.repository

import kotlinx.coroutines.flow.StateFlow
import org.example.product.model.Product

interface ProductRepository {
    // #### State ####
    val products: StateFlow<List<Product>>
    // #### Action ####
    suspend fun addProduct(product: Product)
}

