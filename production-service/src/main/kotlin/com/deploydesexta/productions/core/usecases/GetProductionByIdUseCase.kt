package com.deploydesexta.productions.core.usecases

import com.deploydesexta.productions.core.entities.Production

interface GetProductionByIdUseCase {
    suspend fun execute(anId: Long): Production?
}