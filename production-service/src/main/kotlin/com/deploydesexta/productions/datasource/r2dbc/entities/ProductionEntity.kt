package com.deploydesexta.productions.datasource.r2dbc.entities

import com.deploydesexta.productions.core.entities.Production
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table(name = "productions")
data class ProductionEntity(
    @Id val id: Long,
    @Version val version: Int,
    @Column(value = "name") val name: String,
    @Column(value = "movie_id") val movieId: Long,
    @Column(value = "created_at") val createdAt: Instant,
    @Column(value = "updated_at") val updatedAt: Instant,
) {

    companion object {
        fun from(aProduction: Production): ProductionEntity {
            return ProductionEntity(
                id = aProduction.id,
                version = aProduction.version,
                name = aProduction.name,
                movieId = aProduction.movieId,
                createdAt = aProduction.createdAt,
                updatedAt = aProduction.updatedAt,
            )
        }
    }
}

fun ProductionEntity.toDomain() = Production(
    id = id,
    version = version,
    name = name,
    movieId = movieId,
    createdAt = createdAt,
    updatedAt = updatedAt,
)