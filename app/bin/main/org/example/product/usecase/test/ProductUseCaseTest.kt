package org.example.product.usecase.test


import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.example.product.model.Product
import org.example.product.repository.ProductRepository
import org.example.product.repository.MemoryProductRepository
import org.example.product.usecase.ProductUseCase
import org.example.product.usecase.ProductUseCaseImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ProductUseCaseTest {
    private lateinit var productUseCase: ProductUseCase

    @BeforeEach
    fun setup() {
        productUseCase = ProductUseCaseImpl(MemoryProductRepository())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `add product adds product to repository`() = runBlockingTest{

        val product = Product(1, "Test Product", 100.0)
        productUseCase.addProduct(product)

        val products = productUseCase.products
        assertEquals(1, products.value.size)
        assertEquals(product, products.value[0])
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `add product adds product to repository order change`() = runBlockingTest{

        val products = productUseCase.products

        val product = Product(1, "Test Product", 100.0)
        productUseCase.addProduct(product)

        assertEquals(1, products.value.size)
        assertEquals(product, products.value[0])
    }
}
