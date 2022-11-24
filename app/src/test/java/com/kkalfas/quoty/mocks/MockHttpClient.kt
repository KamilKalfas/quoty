package com.kkalfas.quoty.mocks

import com.kkalfas.quoty.network.installDefaultHeaders
import com.kkalfas.quoty.network.installJSONSerialization
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*

class InternalServerError : Exception("InternalServerError")

fun mockClientRespondWith(
    json: String,
    status: HttpStatusCode = HttpStatusCode.OK,
    headers: Headers = headersOf(HttpHeaders.ContentType, "application/json"),
    isSuccess: Boolean = true
) = HttpClient(MockEngine) {
    installDefaultHeaders()
    installJSONSerialization()
    engine {
        addHandler {
            if (!isSuccess) throw InternalServerError()
            respond(
                content = json,
                status = status,
                headers = headers
            )
        }
    }
}
