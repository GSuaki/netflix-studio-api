package com.deploydesexta.talents.transport.rest

import com.deploydesexta.talents.core.usecases.CreateTalentUseCase
import com.deploydesexta.talents.core.usecases.GetAllTalentsUseCase
import com.deploydesexta.talents.core.usecases.GetTalentByIdUseCase
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Component
class TalentWebAdapter(
    private val createTalentUseCase: CreateTalentUseCase,
    private val getTalentByIdUseCase: GetTalentByIdUseCase,
    private val getAllTalentsUseCase: GetAllTalentsUseCase,
) {

    suspend fun create(req: ServerRequest): ServerResponse {
        val aCommand = req.awaitBody(CreateTalentUseCase.NewTalentCommand::class)
        val aTalent = createTalentUseCase.execute(aCommand)
        return ServerResponse
            .created(URI.create("/talents/${aTalent.id}"))
            .bodyValueAndAwait(aTalent)
    }

    suspend fun getById(req: ServerRequest): ServerResponse {
        val anId = req.pathVariable("id").toLong()
        return getTalentByIdUseCase.execute(anId)
            ?.let { ServerResponse.ok().bodyValueAndAwait(it) }
            ?: ServerResponse.notFound().buildAndAwait()
    }

    suspend fun getAll(req: ServerRequest): ServerResponse {
        val talents = getAllTalentsUseCase.execute()
        return ServerResponse.ok().bodyValueAndAwait(talents)
    }
}