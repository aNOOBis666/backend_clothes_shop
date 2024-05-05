package com.dlegin.profile.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory


@Configuration
@EnableKafka
class KafkaConsumerConfig {

    @Bean
    fun kafkaConsumer(): ConsumerFactory<String, Double> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java

        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaConsumerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Double> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Double>()
        factory.consumerFactory = kafkaConsumer()
        return factory
    }
}