package com.deploydesexta.movies.core.repositories

import com.deploydesexta.movies.core.entities.Movie

interface MovieRepository {

    suspend fun create(aMovie: Movie): Movie

    suspend fun getById(anId: Long): Movie?
}