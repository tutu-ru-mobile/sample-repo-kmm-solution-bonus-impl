package com.sample

import kotlinx.coroutines.CoroutineScope

expect fun testCoroutine(block: suspend CoroutineScope.() -> Unit)
