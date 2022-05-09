package com.deploydesexta.movies.configuration

import com.deploydesexta.movies.core.repositories.MovieRepository
import com.deploydesexta.movies.core.repositories.ProductionRepository
import com.deploydesexta.movies.core.repositories.TalentRepository
import com.deploydesexta.movies.core.usecases.CreateMovieUseCase
import com.deploydesexta.movies.core.usecases.GetMovieByIdUseCase
import com.deploydesexta.movies.core.usecases.impl.DefaultCreateMovieUseCase
import com.deploydesexta.movies.core.usecases.impl.DefaultGetMovieByIdUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfig(
    private val movieRepository: MovieRepository,
    private val productionRepository: ProductionRepository,
    private val talentRepository: TalentRepository,
) {

    @Bean
    fun createMovieUseCase(): CreateMovieUseCase {
        return DefaultCreateMovieUseCase(
            movieRepository,
            productionRepository,
            talentRepository,
        )
    }

    @Bean
    fun getMovieByIdUseCase(): GetMovieByIdUseCase {
        return DefaultGetMovieByIdUseCase(movieRepository)
    }
}