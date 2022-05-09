package com.deploydesexta.talents.core.usecases.impl

import com.deploydesexta.talents.core.entities.Talent
import com.deploydesexta.talents.core.repositories.TalentRepository
import com.deploydesexta.talents.core.usecases.GetAllTalentsUseCase

class DefaultGetAllTalentsUseCase(
    private val talentRepository: TalentRepository,
) : GetAllTalentsUseCase {

    override suspend fun execute(): List<Talent> {
        return talentRepository.findAll()
    }
}