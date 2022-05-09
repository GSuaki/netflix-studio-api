package com.deploydesexta.movies.core.usecases.impl

import com.deploydesexta.movies.core.entities.Movie
import com.deploydesexta.movies.core.repositories.MovieRepository
import com.deploydesexta.movies.core.repositories.ProductionRepository
import com.deploydesexta.movies.core.repositories.ProductionRepository.NewProductionCommand
import com.deploydesexta.movies.core.repositories.TalentRepository
import com.deploydesexta.movies.core.usecases.CreateMovieUseCase
import com.deploydesexta.movies.core.usecases.CreateMovieUseCase.NewMovieCommand

class DefaultCreateMovieUseCase(
    private val movieRepository: MovieRepository,
    private val productionRepository: ProductionRepository,
    private val talentRepository: TalentRepository,
) : CreateMovieUseCase {

    override suspend fun execute(aCommand: NewMovieCommand): Movie {
        val (title, actorsIds) = aCommand

        val actors = talentRepository.getAll(actorsIds)

        val aMovie = Movie.newMovie(title, actors)
        movieRepository.create(aMovie)

        productionRepository.create(
            NewProductionCommand(name = "$title Studios", movieId = aMovie.id)
        )

        return aMovie
    }
}