package com.deploydesexta.productions.core.repositories

import com.deploydesexta.productions.core.entities.Production

interface ProductionRepository {

    suspend fun create(aProduction: Production): Production

    suspend fun findAll(): List<Production>

    suspend fun getById(anId: Long): Production?

    suspend fun getByMovieId(anId: Long): Production?
}