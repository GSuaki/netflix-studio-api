package com.deploydesexta.talents.core.usecases.impl

import com.deploydesexta.talents.core.entities.Talent
import com.deploydesexta.talents.core.repositories.TalentRepository
import com.deploydesexta.talents.core.usecases.CreateTalentUseCase
import com.deploydesexta.talents.core.usecases.CreateTalentUseCase.NewTalentCommand

class DefaultCreateTalentUseCase(
    private val talentRepository: TalentRepository,
) : CreateTalentUseCase {

    override suspend fun execute(aCommand: NewTalentCommand): Talent {
        val (name, document, role) = aCommand
        val aTalent = Talent.newTalent(name, document, role)
        return talentRepository.save(aTalent)
    }
}