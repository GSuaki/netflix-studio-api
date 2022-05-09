package com.deploydesexta.movies.transport.rest

import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse

interface MovieResource {

    fun movieRoutes(): RouterFunction<ServerResponse>
}