package com.deploydesexta.talents.transport

import com.deploydesexta.talents.transport.rest.TalentWebAdapter
import com.deploydesexta.talents.transport.rest.TalentResource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class APIRouter(
    private val talentHandler: TalentWebAdapter,
) : TalentResource {

    @Bean
    fun rootRouter() = coRouter {
        add(talentRoutes())
    }

    override fun talentRoutes() = coRouter {
        filter(ensureClientId)

        path("/talents").nest {
            GET("", talentHandler::getAll)
            GET("/{id}", talentHandler::getById)
            POST("", talentHandler::create)
        }
    }
}