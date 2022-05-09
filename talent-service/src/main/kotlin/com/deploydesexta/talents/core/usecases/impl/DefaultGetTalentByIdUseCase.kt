package com.deploydesexta.talents.core.usecases.impl

import com.deploydesexta.talents.core.entities.Talent
import com.deploydesexta.talents.core.repositories.TalentRepository
import com.deploydesexta.talents.core.usecases.GetTalentByIdUseCase

class DefaultGetTalentByIdUseCase(
    private val talentRepository: TalentRepository,
) : GetTalentByIdUseCase {

    override suspend fun execute(anId: Long): Talent? {
        return talentRepository.findById(anId)
    }
}