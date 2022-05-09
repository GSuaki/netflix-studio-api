package com.deploydesexta.talents.configuration

import com.deploydesexta.talents.core.entities.Talent
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig(
        @Autowired private val factory: RedisConnectionFactory,
        @Autowired private val objectMapper: ObjectMapper,
) {

    @Bean
    fun reactiveRedisTemplate(factory: ReactiveRedisConnectionFactory): ReactiveRedisTemplate<String, Talent> {
        val serializer = Jackson2JsonRedisSerializer(Talent::class.java).apply {
            setObjectMapper(objectMapper)
        }

        val context = RedisSerializationContext
                .newSerializationContext<String, Talent>(StringRedisSerializer())
                .value(serializer)
                .build()

        return ReactiveRedisTemplate(factory, context)
    }

//    @PreDestroy
    fun cleanRedis() {
        factory.connection.flushDb()
    }
}