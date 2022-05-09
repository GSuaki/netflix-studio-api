package com.deploydesexta.productions.core.usecases

import com.deploydesexta.productions.core.entities.Production

interface GetAllProductionsUseCase {
    suspend fun execute(): List<Production>
}