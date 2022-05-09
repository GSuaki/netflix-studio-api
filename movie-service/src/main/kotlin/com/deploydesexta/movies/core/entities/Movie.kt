package com.deploydesexta.movies.core.entities

import com.deploydesexta.movies.utils.SnowflakeID
import java.time.Instant

data class Movie(
    val id: Long,
    val version: Int,
    val title: String,
    val status: MovieStatus,
    val actors: List<Talent>,
    val createdAt: Instant,
    val updatedAt: Instant,
) {

    companion object {

        fun newMovie(title: String, actors: List<Talent>): Movie {
            val id = SnowflakeID.nextId()
            val now = Instant.now()
            return Movie(
                id = id,
                version = 0,
                title = title,
                status = MovieStatus.RECORDING,
                actors = actors,
                createdAt = now,
                updatedAt = now,
            )
        }
    }
}
