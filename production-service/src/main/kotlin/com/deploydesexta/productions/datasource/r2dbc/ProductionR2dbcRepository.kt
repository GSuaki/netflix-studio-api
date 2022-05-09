package com.deploydesexta.productions.datasource.r2dbc

import com.deploydesexta.productions.datasource.r2dbc.entities.ProductionEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Mono

interface ProductionR2dbcRepository : R2dbcRepository<ProductionEntity, Long> {

    fun findByMovieId(anId: Long): Mono<ProductionEntity>
}