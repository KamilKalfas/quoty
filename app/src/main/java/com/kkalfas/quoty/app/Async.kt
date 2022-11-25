package com.kkalfas.quoty.app

import io.ktor.client.plugins.*

sealed class Async<out R> {
    data class Success<out T>(val data: T) : Async<T>()
    data class Error(val failure: Failure) : Async<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$failure]"
        }
    }

    companion object {

        inline fun <reified T : Any> execute(block: () -> T): Async<T> {
            return try {
                Success(block())
            } catch (e: Exception) {
                Error(e.toCustomExceptions())
            }
        }

        inline fun <reified T : Any> Async<T>.then(
            onSuccess: (T) -> Unit,
            onError: (Failure) -> Unit
        ) = when (this) {
            is Error -> onError(this.failure)
            is Success -> onSuccess(this.data)
        }
    }
}


sealed class Failure {
    data class ServerFailure(val e: Exception) : Failure()
    data class HttpError(val statusCode: Int, val e: Exception) : Failure()
    data class GenericError(val e: Exception) : Failure()

    override fun toString(): String = this.javaClass.simpleName
}

fun Exception.toCustomExceptions(): Failure = when (this) {
    is ServerResponseException -> Failure.ServerFailure(this)
    is ClientRequestException -> Failure.HttpError(this.response.status.value, this)
    else -> Failure.GenericError(this)
}
