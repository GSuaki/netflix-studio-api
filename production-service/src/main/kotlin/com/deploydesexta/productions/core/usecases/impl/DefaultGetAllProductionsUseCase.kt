package com.deploydesexta.productions.core.usecases.impl

import com.deploydesexta.productions.core.entities.Production
import com.deploydesexta.productions.core.repositories.ProductionRepository
import com.deploydesexta.productions.core.usecases.GetAllProductionsUseCase

class DefaultGetAllProductionsUseCase(
    private val productionRepository: ProductionRepository
) : GetAllProductionsUseCase {

    override suspend fun execute(): List<Production> {
        return productionRepository.findAll()
    }
}