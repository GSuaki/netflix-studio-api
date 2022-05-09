package com.deploydesexta.movies.datasource.persistence.entities

import com.deploydesexta.movies.core.entities.Movie
import com.deploydesexta.movies.core.entities.Talent
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "movies_talents")
data class MovieTalentEntity(
    @Id @Column(value = "talent_id") val talentId: Long,
    @Version val version: Int,
    @Column(value = "movie_id") val movieId: Long,
) {

    companion object {
        fun from(aMovie: Movie, aTalent: Talent): MovieTalentEntity {
            return MovieTalentEntity(
                talentId = aTalent.id,
                movieId = aMovie.id,
                version = 0,
            )
        }
    }
}

fun movieTalentEntityOf(aMovie: Movie, aTalent: Talent): MovieTalentEntity {
    return MovieTalentEntity.from(aMovie, aTalent)
}