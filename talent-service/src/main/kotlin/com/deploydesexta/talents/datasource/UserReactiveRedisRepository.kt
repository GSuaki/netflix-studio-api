package com.deploydesexta.talents.datasource

import com.deploydesexta.talents.core.entities.Talent
import com.deploydesexta.talents.core.repositories.TalentRepository
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitFirstOrNull
import mu.KotlinLogging
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.redis.core.setAndAwait
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {}

@Component
class UserReactiveRedisRepository(
    private val operations: ReactiveRedisOperations<String, Talent>,
) : TalentRepository {

    override suspend fun findById(anId: Long): Talent? {
        log.info { "Getting Talent: $anId" }
        return operations.opsForValue()
            .get(anId.toString())
            .doOnNext { log.info { "Talent retrieved: $anId" } }
            .doOnError { log.error(it) { "Could not retrieve Talent: $anId" } }
            .awaitFirstOrNull()
    }

    override suspend fun findAll(): List<Talent> {
        val valueOps = this.operations.opsForValue()
        return this.operations.keys("*")
            .flatMap { valueOps.get(it) }
            .collectList()
            .awaitFirstOrElse { emptyList() }
    }

    override suspend fun findAllByNameContaining(filter: String): List<Talent> {
        val valueOps = this.operations.opsForValue()
        return this.operations.keys("*")
            .flatMap { valueOps.get(it) }
            .filter { it.name.contains(filter) }
            .collectList()
            .awaitFirstOrElse { emptyList() }
    }

    override suspend fun save(anUser: Talent): Talent {
        var success = false

        while (!success) {
            success = this.operations.opsForValue()
                .setAndAwait(anUser.id.toString(), anUser)
        }

        return anUser
    }
}