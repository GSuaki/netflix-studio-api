package com.deploydesexta.productions.transport.rest

import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse

interface ProductionResource {

    fun productionRoutes(): RouterFunction<ServerResponse>
}