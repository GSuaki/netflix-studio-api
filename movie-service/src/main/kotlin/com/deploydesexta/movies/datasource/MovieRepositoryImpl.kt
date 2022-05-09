package com.deploydesexta.movies.datasource

import com.deploydesexta.movies.core.entities.Movie
import com.deploydesexta.movies.core.entities.Talent
import com.deploydesexta.movies.core.repositories.MovieRepository
import com.deploydesexta.movies.core.repositories.TalentRepository
import com.deploydesexta.movies.datasource.persistence.MovieR2dbcRepository
import com.deploydesexta.movies.datasource.persistence.MovieTalentR2dbcRepository
import com.deploydesexta.movies.datasource.persistence.entities.MovieEntity
import com.deploydesexta.movies.datasource.persistence.entities.movieTalentEntityOf
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono
import reactor.kotlin.core.util.function.component1
import reactor.kotlin.core.util.function.component2

private val log = KotlinLogging.logger {}

@Component
class MovieRepositoryImpl(
    private val movieRepository: MovieR2dbcRepository,
    private val movieTalentRepository: MovieTalentR2dbcRepository,
    private val talentRepository: TalentRepository,
) : MovieRepository {

    @Transactional
    override suspend fun create(aMovie: Movie): Movie {
        val aPersistedProduct = movieRepository.save(MovieEntity.from(aMovie))
            .awaitFirst()

        movieTalentRepository.deleteAllByMovieId(aMovie.id)
            .awaitFirstOrNull()

        aMovie.actors.map { movieTalentEntityOf(aMovie, it) }
            .let { movieTalentRepository.saveAll(it) }
            .collectList()
            .awaitFirst()

        return toDomain(
            anEntity = aPersistedProduct,
            actors = aMovie.actors,
        )
    }

    override suspend fun getById(anId: Long): Movie? {
        val moviePromise = this.movieRepository.findById(anId)
        val talentsIdsPromise = this.movieTalentRepository.findAllTalentIdByMovieId(anId).collectList()

        val (movie, talentsIds) = Mono.zip(moviePromise, talentsIdsPromise)
            .awaitFirstOrNull() ?: return null

        val talents = talentRepository.getAll(talentsIds)

        return toDomain(movie, talents)
            .also { log.info { "Production retrieved: $anId" } }
    }

    private fun toDomain(anEntity: MovieEntity, actors: List<Talent>) = Movie(
        id = anEntity.id,
        version = anEntity.version,
        title = anEntity.title,
        actors = actors,
        status = anEntity.status,
        createdAt = anEntity.createdAt,
        updatedAt = anEntity.updatedAt,
    )
}