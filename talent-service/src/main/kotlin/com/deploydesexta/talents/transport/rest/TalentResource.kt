package com.deploydesexta.talents.transport.rest

import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse

interface TalentResource {

    fun talentRoutes(): RouterFunction<ServerResponse>
}