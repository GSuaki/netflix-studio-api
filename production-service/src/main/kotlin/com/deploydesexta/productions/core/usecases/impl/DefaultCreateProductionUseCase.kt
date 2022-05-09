package com.deploydesexta.productions.core.usecases.impl

import com.deploydesexta.productions.core.entities.Production
import com.deploydesexta.productions.core.repositories.ProductionRepository
import com.deploydesexta.productions.core.usecases.CreateProductionUseCase
import com.deploydesexta.productions.core.usecases.CreateProductionUseCase.NewProductionCommand

class DefaultCreateProductionUseCase(
    private val productionRepository: ProductionRepository,
) : CreateProductionUseCase {

    override suspend fun execute(aCommand: NewProductionCommand): Production {
        val (name, movieId) = aCommand
        val aProduction = Production.newProduction(name, movieId)
        productionRepository.create(aProduction)
        return aProduction
    }
}