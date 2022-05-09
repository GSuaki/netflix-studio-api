package com.deploydesexta.talents.core.repositories

import com.deploydesexta.talents.core.entities.Talent

interface TalentRepository {

    suspend fun findById(anId: Long): Talent?

    suspend fun findAll(): List<Talent>

    suspend fun findAllByNameContaining(filter: String): List<Talent>

    suspend fun save(anUser: Talent): Talent
}