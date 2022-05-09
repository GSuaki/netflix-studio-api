package com.deploydesexta.movies.datasource.persistence

import com.deploydesexta.movies.datasource.persistence.entities.MovieTalentEntity
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface MovieTalentR2dbcRepository : R2dbcRepository<MovieTalentEntity, Long> {

    fun deleteAllByMovieId(anId: Long): Mono<Void>

    fun findAllTalentIdByMovieId(anId: Long): Flux<Long>
}