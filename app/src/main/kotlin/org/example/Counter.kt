package org.example

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class Counter {
    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()

    suspend fun increment() {
        delay(1000)
        _count.value++

        delay(1000)
        _count.value += 2
    }
}