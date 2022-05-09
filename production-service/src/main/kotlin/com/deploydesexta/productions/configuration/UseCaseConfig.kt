package com.deploydesexta.productions.configuration

import com.deploydesexta.productions.core.repositories.ProductionRepository
import com.deploydesexta.productions.core.usecases.CreateProductionUseCase
import com.deploydesexta.productions.core.usecases.GetAllProductionsUseCase
import com.deploydesexta.productions.core.usecases.GetProductionByIdUseCase
import com.deploydesexta.productions.core.usecases.impl.DefaultCreateProductionUseCase
import com.deploydesexta.productions.core.usecases.impl.DefaultGetAllProductionsUseCase
import com.deploydesexta.productions.core.usecases.impl.DefaultGetProductionByIdUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfig(
    private val productionRepository: ProductionRepository,
) {

    @Bean
    fun createProductionUseCase(): CreateProductionUseCase {
        return DefaultCreateProductionUseCase(productionRepository)
    }

    @Bean
    fun getProductionByIdUseCase(): GetProductionByIdUseCase {
        return DefaultGetProductionByIdUseCase(productionRepository)
    }

    @Bean
    fun getAllProductionsUseCase(): GetAllProductionsUseCase {
        return DefaultGetAllProductionsUseCase(productionRepository)
    }
}