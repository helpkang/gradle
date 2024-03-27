package org.example.product.usecase.test


import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.yield
import org.example.product.model.Product
import org.example.product.repository.ProductRepository
import org.example.product.repository.MemoryProductRepository
import org.example.product.usecase.ProductUseCase
import org.example.product.usecase.ProductUseCaseImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FilterProductUseCaseTest {
    private lateinit var productUseCase: ProductUseCase

    @BeforeEach
    fun setup() {
        productUseCase = ProductUseCaseImpl(MemoryProductRepository())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `filter product`() = runBlockingTest {
        var product = Product(1, "Test Product", 100.0)
        productUseCase.addProduct(product)
        product = Product(2, "Product", 30.0)
        productUseCase.addProduct(product)

        val products = productUseCase.products
        assertEquals(2, products.value.size)
        Thread.sleep(20)
        val filterProduct = productUseCase.filterProduct
        assertEquals(2, filterProduct.value.size)
        productUseCase.setFilterStr("Test Product")
        Thread.sleep(1)
        assertEquals(1, filterProduct.value.size)

    }


}
