package com.deploydesexta.talents.core.usecases

import com.deploydesexta.talents.core.entities.Talent

interface GetTalentByIdUseCase {
    suspend fun execute(anId: Long): Talent?
}