package com.deploydesexta.productions.core.entities

import com.deploydesexta.productions.utils.SnowflakeID
import java.time.Instant

data class Production(
    val id: Long,
    val movieId: Long,
    val version: Int,
    val name: String,
    val createdAt: Instant,
    val updatedAt: Instant,
) {

    companion object {
        fun newProduction(name: String, movieId: Long): Production {
            val id = SnowflakeID.nextId()
            val now = Instant.now()
            return Production(
                id = id,
                version = 0,
                movieId = movieId,
                name = name,
                createdAt = now,
                updatedAt = now,
            )
        }
    }
}