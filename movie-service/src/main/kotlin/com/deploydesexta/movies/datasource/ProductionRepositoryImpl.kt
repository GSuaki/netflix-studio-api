package com.deploydesexta.movies.datasource

import com.deploydesexta.movies.core.entities.Production
import com.deploydesexta.movies.core.repositories.ProductionRepository
import com.deploydesexta.movies.core.repositories.ProductionRepository.NewProductionCommand
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

private val log = KotlinLogging.logger {}

@Component
class ProductionRepositoryImpl(
    @Qualifier("productionWebClient") private val webClient: WebClient,
) : ProductionRepository {

    override suspend fun getById(anId: Long): Production? {
        log.info { "Getting Studio production: $anId" }
        return this.webClient.get()
            .uri("/productions/$anId?clientId=123")
            .accept(MediaType.APPLICATION_JSON)
            .exchangeToMono { it.bodyToMono(Production::class.java) }
            .doOnNext { log.info { "Studio production retrieved: $anId" } }
            .awaitFirstOrNull()
    }

    override suspend fun create(aCommand: NewProductionCommand): Production {
        val movieId = aCommand.movieId
        log.info { "Creating Studio production for Movie: $movieId" }
        return this.webClient.post()
            .uri("/productions?clientId=123")
            .bodyValue(aCommand)
            .accept(MediaType.APPLICATION_JSON)
            .exchangeToMono { it.bodyToMono(Production::class.java) }
            .doOnError { log.error(it) { "Could not create Studio production for $movieId" } }
            .doOnNext { log.info { "Studio production retrieved: $movieId" } }
            .awaitFirst()
    }
}