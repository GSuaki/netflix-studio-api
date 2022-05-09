package com.deploydesexta.movies.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    private val objectMapper: ObjectMapper,
) {

    @Bean
    fun talentWebClient(
        @Value("\${services.talent}") uri: String,
    ): WebClient {
        return createWebClient(uri)
    }

    @Bean
    fun productionWebClient(
        @Value("\${services.production}") uri: String,
    ): WebClient {
        return createWebClient(uri)
    }

    private fun createWebClient(uri: String) = WebClient.builder()
        .baseUrl(uri)
        .codecs {
            val codecs = it.defaultCodecs()
            codecs.jackson2JsonDecoder(Jackson2JsonDecoder(objectMapper))
            codecs.jackson2JsonEncoder(Jackson2JsonEncoder(objectMapper))
        }
        .build()
}