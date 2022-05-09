package com.deploydesexta.talents.configuration

import com.deploydesexta.talents.core.repositories.TalentRepository
import com.deploydesexta.talents.core.usecases.CreateTalentUseCase
import com.deploydesexta.talents.core.usecases.GetAllTalentsUseCase
import com.deploydesexta.talents.core.usecases.GetTalentByIdUseCase
import com.deploydesexta.talents.core.usecases.impl.DefaultCreateTalentUseCase
import com.deploydesexta.talents.core.usecases.impl.DefaultGetAllTalentsUseCase
import com.deploydesexta.talents.core.usecases.impl.DefaultGetTalentByIdUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfig(
    private val talentRepository: TalentRepository,
) {

    @Bean
    fun createTalentUseCase(): CreateTalentUseCase {
        return DefaultCreateTalentUseCase(talentRepository)
    }

    @Bean
    fun getTalentByIdUseCase(): GetTalentByIdUseCase {
        return DefaultGetTalentByIdUseCase(talentRepository)
    }

    @Bean
    fun getAllTalentsUseCase(): GetAllTalentsUseCase {
        return DefaultGetAllTalentsUseCase(talentRepository)
    }
}