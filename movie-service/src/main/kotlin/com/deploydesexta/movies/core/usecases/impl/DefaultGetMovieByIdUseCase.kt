package com.deploydesexta.movies.core.usecases.impl

import com.deploydesexta.movies.core.entities.Movie
import com.deploydesexta.movies.core.repositories.MovieRepository
import com.deploydesexta.movies.core.usecases.GetMovieByIdUseCase

class DefaultGetMovieByIdUseCase(
    private val movieRepository: MovieRepository,
) : GetMovieByIdUseCase {

    override suspend fun execute(anId: Long): Movie? {
        return movieRepository.getById(anId)
    }
}