package com.deploydesexta.movies.datasource.persistence.entities

import com.deploydesexta.movies.core.entities.Movie
import com.deploydesexta.movies.core.entities.MovieStatus
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table(name = "movies")
data class MovieEntity(
    @Id val id: Long,
    @Version val version: Int,
    @Column(value = "title") val title: String,
    @Column(value = "status") val status: MovieStatus,
    @Column(value = "created_at") val createdAt: Instant,
    @Column(value = "updated_at") val updatedAt: Instant,
) {

    companion object {
        fun from(aMovie: Movie): MovieEntity {
            return MovieEntity(
                id = aMovie.id,
                version = aMovie.version,
                title = aMovie.title,
                status = aMovie.status,
                createdAt = aMovie.createdAt,
                updatedAt = aMovie.updatedAt,
            )
        }
    }
}