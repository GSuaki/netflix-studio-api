package com.deploydesexta.movies.transport.graphql

import com.deploydesexta.movies.core.entities.Talent
import com.deploydesexta.movies.core.repositories.TalentRepository
import com.deploydesexta.movies.datasource.persistence.MovieR2dbcRepository
import com.deploydesexta.movies.datasource.persistence.MovieTalentR2dbcRepository
import com.deploydesexta.movies.datasource.persistence.entities.MovieEntity
import com.netflix.graphql.dgs.*
import kotlinx.coroutines.reactive.awaitFirst

@DgsComponent
class MovieDataFetcher(
    private val movieRepository: MovieR2dbcRepository,
    private val movieTalentRepository: MovieTalentR2dbcRepository,
    private val talentRepository: TalentRepository,
) {

    @DgsQuery
    suspend fun movies(@InputArgument("title") titleFilter: String? = ""): List<MovieEntity> {
        val promise = if (titleFilter != null) {
            movieRepository.findByTitleContains(titleFilter)
        } else {
            movieRepository.findAll()
        }
        return promise.collectList().awaitFirst()
    }

    @DgsData(parentType = "Movie", field = "talents")
    suspend fun talentFetcher(env: DgsDataFetchingEnvironment): List<Talent> {
        val movie = env.getSource<MovieEntity>()

        val actors = this.movieTalentRepository.findAllTalentIdByMovieId(movie.id)
            .collectList()
            .awaitFirst()

        return talentRepository.getAll(actors)
    }
}