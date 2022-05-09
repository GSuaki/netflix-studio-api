package com.deploydesexta.productions.transport

import com.deploydesexta.productions.transport.rest.ProductionResource
import com.deploydesexta.productions.transport.rest.ProductionWebAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class APIRouter(
    private val productionHandler: ProductionWebAdapter,
) : ProductionResource {

    @Bean
    fun rootRouter() = coRouter {
        add(productionRoutes())
    }

    override fun productionRoutes() = coRouter {
        filter(ensureClientId)

        path("/productions").nest {
            GET("", productionHandler::getAll)
            GET("/{id}", productionHandler::getById)
            POST("", productionHandler::create)
        }
    }
}