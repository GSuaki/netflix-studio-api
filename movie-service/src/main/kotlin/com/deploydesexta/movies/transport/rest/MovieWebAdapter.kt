package com.deploydesexta.movies.transport.rest

import com.deploydesexta.movies.core.usecases.CreateMovieUseCase
import com.deploydesexta.movies.core.usecases.CreateMovieUseCase.NewMovieCommand
import com.deploydesexta.movies.core.usecases.GetMovieByIdUseCase
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Component
class MovieWebAdapter(
    private val createMovieUseCase: CreateMovieUseCase,
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
) {

    suspend fun create(req: ServerRequest): ServerResponse {
        val aCommand = req.awaitBody(NewMovieCommand::class)
        val aTalent = createMovieUseCase.execute(aCommand)
        return ServerResponse
            .created(URI.create("/movies/${aTalent.id}"))
            .bodyValueAndAwait(aTalent)
    }

    suspend fun getById(req: ServerRequest): ServerResponse {
        val anId = req.pathVariable("id").toLong()
        return getMovieByIdUseCase.execute(anId)
            ?.let { ServerResponse.ok().bodyValueAndAwait(it) }
            ?: ServerResponse.notFound().buildAndAwait()
    }
}