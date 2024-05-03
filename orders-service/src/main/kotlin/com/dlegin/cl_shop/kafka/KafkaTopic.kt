package com.dlegin.cl_shop.kafka

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder


@Configuration
class KafkaTopic {
    @Bean
    fun topic1(): NewTopic {
        return TopicBuilder.name("topic-1").build()
    }

    @Bean
    fun topic2(): NewTopic {
        return TopicBuilder.name("topic-2").partitions(3).build()
    }
}