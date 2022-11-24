package com.kkalfas.quoty.network

/**
 * network layer separation of 3rd party
 */
interface NetworkClientProvider<T> {
    val client: T
}
