package com.sample

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

actual fun testCoroutine(block: suspend CoroutineScope.() -> Unit) =
    runBlocking {
        block()
    }
