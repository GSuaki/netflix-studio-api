package com.deploydesexta.talents.core.usecases

import com.deploydesexta.talents.core.entities.Talent

interface GetAllTalentsUseCase {
    suspend fun execute(): List<Talent>
}