package org.example.product.usecase

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.example.product.common.CoroutineScopeUse
import org.example.product.model.Product
import org.example.product.repository.ProductRepository

class ProductUseCaseImpl(private val productRepository: ProductRepository) : ProductUseCase,
    CoroutineScopeUse() {

    override val products = productRepository.products

    private val _filterStr = MutableStateFlow("");
    override fun setFilterStr(value: String) {
        _filterStr.value = value
    }

    private val _filterProduct = MutableStateFlow<List<Product>>(emptyList())
    override val filterProduct = _filterProduct.asStateFlow()

    override suspend fun addProduct(product: Product) {
        productRepository.addProduct(product)
    }

    init {
        changeProductsAndFilterStrToFilterProduct()
    }

    /**
     * products 와 filterStr 이 바뀌면 filterProduct 을 갱신 한다.
     */
    private fun changeProductsAndFilterStrToFilterProduct() = launch {
        products.combine(_filterStr) { products, filterStr ->
            products.filter {
                it.name.contains(filterStr)
            }
        }.distinctUntilChanged().collect {
            _filterProduct.value = it
        }
    }
}