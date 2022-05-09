package com.deploydesexta.productions.core.usecases

import com.deploydesexta.productions.core.entities.Production

interface CreateProductionUseCase {

    suspend fun execute(aCommand: NewProductionCommand): Production

    data class NewProductionCommand(
        val name: String,
        val movieId: Long,
    )
}