package com.deploydesexta.talents.core.entities

import com.deploydesexta.talents.utils.SnowflakeID
import java.time.Instant

data class Talent(
    val id: Long,
    val version: Int,
    val name: String,
    val document: String,
    val role: String,
    val createdAt: Instant,
    val updatedAt: Instant,
) {

    companion object {
        fun newTalent(name: String, document: String, role: String): Talent {
            val id = SnowflakeID.nextId()
            val now = Instant.now()
            return Talent(
                id = id,
                version = 0,
                name = name,
                document = document,
                role = role,
                createdAt = now,
                updatedAt = now,
            )
        }
    }
}