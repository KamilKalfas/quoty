package com.kkalfas.quoty.network.ktor

import io.ktor.client.request.*

private const val AUTHORIZATION_HEADER_KEY = "Authorization"
private const val USER_TOKEN_HEADER_KEY = "User-Token"
private const val AUTHORIZATION_HEADER_VALUE = "Token token=%s"

fun HttpRequestBuilder.authHeader(token: String) {
    headers.append(AUTHORIZATION_HEADER_KEY, String.format(AUTHORIZATION_HEADER_VALUE, token))
}

fun HttpRequestBuilder.userTokenHeader(token: String) {
    headers.append(USER_TOKEN_HEADER_KEY, token)
}
