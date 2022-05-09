package com.deploydesexta.movies.transport

import com.deploydesexta.movies.transport.rest.MovieResource
import com.deploydesexta.movies.transport.rest.MovieWebAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class APIRouter(
    private val movieHandler: MovieWebAdapter,
) : MovieResource {

    @Bean
    fun rootRouter() = coRouter {
        add(movieRoutes())
    }

    override fun movieRoutes() = coRouter {
        filter(ensureClientId)

        path("/movies").nest {
            GET("/{id}", movieHandler::getById)
            POST("", movieHandler::create)
        }
    }
}