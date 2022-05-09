package com.deploydesexta.movies

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories
class MovieApplication

fun main(args: Array<String>) {
    runApplication<MovieApplication>(*args)
}
