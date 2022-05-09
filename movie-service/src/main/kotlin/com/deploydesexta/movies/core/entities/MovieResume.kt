package com.deploydesexta.movies.core.entities

import java.time.Instant

data class MovieResume(
    val id: Long,
    val version: Int,
    val title: String,
    val status: MovieStatus,
    val actorsIds: List<Long>,
    val createdAt: Instant,
    val updatedAt: Instant,
)