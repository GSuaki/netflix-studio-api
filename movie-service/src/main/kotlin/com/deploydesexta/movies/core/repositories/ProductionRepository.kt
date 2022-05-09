package com.deploydesexta.movies.core.repositories

import com.deploydesexta.movies.core.entities.Production

interface ProductionRepository {

    suspend fun getById(anId: Long): Production?

    suspend fun create(aCommand: NewProductionCommand): Production

    data class NewProductionCommand(
        val name: String,
        val movieId: Long,
    )
}