package com.deploydesexta.productions.configuration

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class ObjectMapperConfig {

    @Bean
    fun objectMapper(): ObjectMapper = getInstance()

    companion object {

        fun getInstance(): ObjectMapper = INSTANCE.copy()

        private val INSTANCE: ObjectMapper = Jackson2ObjectMapperBuilder.json()
                .featuresToDisable(
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                )
                .featuresToEnable(
                        MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS,
                        SerializationFeature.WRITE_ENUMS_USING_TO_STRING,
                        DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL,
                )
                .modules(Jdk8Module(), kotlinModule(), JavaTimeModule())
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .build()

        private fun kotlinModule() = KotlinModule.Builder()
                .configure(KotlinFeature.NullIsSameAsDefault, true)
                .build()
    }
}