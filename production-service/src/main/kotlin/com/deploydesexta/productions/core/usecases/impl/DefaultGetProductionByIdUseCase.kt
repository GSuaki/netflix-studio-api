package com.deploydesexta.productions.core.usecases.impl

import com.deploydesexta.productions.core.entities.Production
import com.deploydesexta.productions.core.repositories.ProductionRepository
import com.deploydesexta.productions.core.usecases.GetProductionByIdUseCase

class DefaultGetProductionByIdUseCase(
    private val productionRepository: ProductionRepository,
) : GetProductionByIdUseCase {

    override suspend fun execute(anId: Long): Production? {
        return productionRepository.getById(anId)
    }
}