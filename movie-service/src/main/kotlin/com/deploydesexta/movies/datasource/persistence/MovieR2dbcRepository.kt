package com.deploydesexta.movies.datasource.persistence

import com.deploydesexta.movies.datasource.persistence.entities.MovieEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux


interface MovieR2dbcRepository : R2dbcRepository<MovieEntity, Long> {

    @Query("SELECT * FROM movies WHERE title like $1")
    fun findByTitleContains(name: String?): Flux<MovieEntity>
}