package com.dlegin.cl_shop.kafka

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
@Slf4j
class KafkaSender {

    @Autowired
    lateinit var kafkaTemplate: KafkaTemplate<String, String>

    fun sendMessage(message: String, topicName: String) {
        kafkaTemplate.send(topicName, message);
    }
}