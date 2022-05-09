package com.deploydesexta.talents.core.usecases

import com.deploydesexta.talents.core.entities.Talent
import com.deploydesexta.talents.utils.Faker

interface CreateTalentUseCase {

    suspend fun execute(aCommand: NewTalentCommand): Talent

    data class NewTalentCommand(
        val name: String = Faker.companyName(),
        val document: String = Faker.idNumber(),
        val role: String = Faker.job(),
    )
}