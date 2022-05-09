package com.deploydesexta.movies.core.usecases

import com.deploydesexta.movies.core.entities.Movie

interface GetMovieByIdUseCase {
    suspend fun execute(anId: Long): Movie?
}