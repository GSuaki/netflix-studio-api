package com.deploydesexta.movies.core.repositories

import com.deploydesexta.movies.core.entities.Talent

interface TalentRepository {

    suspend fun getById(anId: Long): Talent?

    suspend fun getAll(ids: List<Long>): List<Talent>
}