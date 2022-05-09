package com.deploydesexta.productions.transport.graphql

import com.deploydesexta.productions.core.entities.Production
import com.deploydesexta.productions.core.repositories.ProductionRepository
import com.netflix.graphql.dgs.*

@DgsComponent
class ProductionDataFetcher(
    private val productionRepository: ProductionRepository,
) {

    @DgsQuery
    suspend fun productionByMovieId(@InputArgument("movieId") movieId: String): Production? {
        return productionRepository.getByMovieId(movieId.toLong())
    }

    @DgsEntityFetcher(name = "Movie")
    fun movie(values: Map<String, Any>): Movie {
        return Movie(id = values["id"] as String)
    }

    @DgsData(parentType = "Movie", field = "production")
    suspend fun productionFetcher(env: DgsDataFetchingEnvironment): Production? {
        val movie = env.getSource<Movie>()
        return productionRepository.getByMovieId(movie.id.toLong())
    }

    data class Movie(
        val id: String,
    )
}