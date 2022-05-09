package com.deploydesexta.movies.utils

import java.util.*
import com.github.javafaker.Faker as FakerService

object Faker {

    private val faker = FakerService.instance(Locale.US)

    fun companyName() = faker.company().name().toString()

    fun idNumber() = faker.idNumber().valid().toString()
}