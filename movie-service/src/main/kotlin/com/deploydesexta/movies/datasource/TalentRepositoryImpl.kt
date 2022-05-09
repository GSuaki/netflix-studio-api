package com.deploydesexta.movies.datasource

import com.deploydesexta.movies.core.entities.Talent
import com.deploydesexta.movies.core.repositories.TalentRepository
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactive.awaitFirstOrNull
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

private val log = KotlinLogging.logger {}

@Component
class TalentRepositoryImpl(
    @Qualifier("talentWebClient") private val webClient: WebClient,
) : TalentRepository {

    override suspend fun getById(anId: Long): Talent? {
        log.info { "Getting talent: $anId" }
        return this.webClient.get()
            .uri("/talents/$anId?clientId=123")
            .accept(MediaType.APPLICATION_JSON)
            .exchangeToMono { it.bodyToMono(Talent::class.java) }
            .doOnNext { log.info { "Talent retrieved: $anId" } }
            .doOnError { log.error(it) { "Could not retrieve Talent: $anId" } }
            .awaitFirstOrNull()
    }

    override suspend fun getAll(ids: List<Long>): List<Talent> = coroutineScope {
        log.info { "Fetching all talents (${ids.size})" }
        ids.map {
            async(start = CoroutineStart.LAZY) {
                getById(it)
            }
        }
            .awaitAll()
            .filterNotNull()
    }
}