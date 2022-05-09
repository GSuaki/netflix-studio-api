package com.deploydesexta.productions.transport.rest

import com.deploydesexta.productions.core.usecases.CreateProductionUseCase
import com.deploydesexta.productions.core.usecases.CreateProductionUseCase.NewProductionCommand
import com.deploydesexta.productions.core.usecases.GetAllProductionsUseCase
import com.deploydesexta.productions.core.usecases.GetProductionByIdUseCase
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.net.URI

@Component
class ProductionWebAdapter(
    private val createProductionUseCase: CreateProductionUseCase,
    private val getProductionByIdUseCase: GetProductionByIdUseCase,
    private val getAllProductionsUseCase: GetAllProductionsUseCase,
) {

    suspend fun create(req: ServerRequest): ServerResponse {
        val aCommand = req.awaitBody(NewProductionCommand::class)
        val aProduction = createProductionUseCase.execute(aCommand)
        return ServerResponse
            .created(URI.create("/productions/${aProduction.id}"))
            .bodyValueAndAwait(aProduction)
    }

    suspend fun getById(req: ServerRequest): ServerResponse {
        val anId = req.pathVariable("id").toLong()
        return getProductionByIdUseCase.execute(anId)
            ?.let { ServerResponse.ok().bodyValueAndAwait(it) }
            ?: ServerResponse.notFound().buildAndAwait()
    }

    suspend fun getAll(req: ServerRequest): ServerResponse {
        val productions = getAllProductionsUseCase.execute()
        return ServerResponse.ok().bodyValueAndAwait(productions)
    }
}