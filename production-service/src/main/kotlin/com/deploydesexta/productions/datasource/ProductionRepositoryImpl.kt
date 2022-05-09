package com.deploydesexta.productions.datasource

import com.deploydesexta.productions.core.entities.Production
import com.deploydesexta.productions.core.repositories.ProductionRepository
import com.deploydesexta.productions.datasource.r2dbc.ProductionR2dbcRepository
import com.deploydesexta.productions.datasource.r2dbc.entities.ProductionEntity
import com.deploydesexta.productions.datasource.r2dbc.entities.toDomain
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitFirstOrNull
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

private val log = KotlinLogging.logger {}

@Component
class ProductionRepositoryImpl(
    private val productionRepository: ProductionR2dbcRepository,
) : ProductionRepository {

    @Transactional
    override suspend fun create(aProduction: Production): Production {
        return productionRepository.save(ProductionEntity.from(aProduction))
            .awaitFirst()
            .toDomain()
    }

    override suspend fun findAll(): List<Production> {
        return this.productionRepository.findAll()
            .collectList()
            .awaitFirstOrElse { emptyList() }
            .map { it.toDomain() }
    }

    override suspend fun getById(anId: Long): Production? {
        return this.productionRepository.findById(anId)
            .awaitFirstOrNull()
            ?.toDomain()
            ?.also { log.info { "Production retrieved: $anId" } }
    }

    override suspend fun getByMovieId(anId: Long): Production? {
        return this.productionRepository.findByMovieId(anId)
            .awaitFirstOrNull()
            ?.toDomain()
            ?.also { log.info { "Production retrieved by Movie: $anId" } }
    }
}