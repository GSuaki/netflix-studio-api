package com.deploydesexta.movies.core.usecases

import com.deploydesexta.movies.core.entities.Movie

interface CreateMovieUseCase {

    suspend fun execute(aCommand: NewMovieCommand): Movie

    data class NewMovieCommand(
        val title: String,
        val actors: List<Long>,
    )
}