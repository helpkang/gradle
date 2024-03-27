package org.example.product.usecase

import kotlinx.coroutines.flow.StateFlow
import org.example.product.model.Product

/**
 * UseCase for Product
 * App 에서는 이 UseCase 를 통해 비즈니스 로직을 처리 하는데
 * Backend 나 React 에서는 Service 라고 부른다
 * (Android 에서 Service 하면 background service 를 의미 하기 때문에 UseCase 라는 용어를 사용)
 */
interface ProductUseCase{
    // #### State ####
    /**
     * Product 목록 StateFlow
     */
    val products: StateFlow<List<Product>>
    /**
     * Product 필터링 StateFlow
     */
    val filterProduct: StateFlow<List<Product>>

    // #### Action ####
    /**
     * Product 필터링 문자열 설정
     */
    fun setFilterStr(value: String)
    /**
     * Product 추가
     */
    suspend fun addProduct(product: Product)
}
