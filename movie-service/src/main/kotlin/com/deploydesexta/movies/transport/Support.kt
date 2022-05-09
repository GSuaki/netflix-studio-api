package com.deploydesexta.movies.transport

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

internal typealias HandlerFn =
        suspend (ServerRequest) -> ServerResponse

internal typealias FilterFn =
        suspend (ServerRequest, HandlerFn) -> ServerResponse

val ensureClientId: FilterFn = { req, next ->
    if (req.queryParam("clientId").isEmpty) {
        ServerResponse.status(401)
            .bodyValueAndAwait("Parameter 'clientId' is required")
    } else {
        next(req)
    }
}