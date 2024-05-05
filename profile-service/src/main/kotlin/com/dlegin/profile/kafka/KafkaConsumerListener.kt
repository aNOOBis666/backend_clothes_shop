package com.dlegin.profile.kafka

import com.dlegin.profile.profile.repository.ProfileRepository
import com.dlegin.profile.profile.repository.findByToken
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service


@Component
@Slf4j
class KafkaConsumerListener {
    @Autowired
    lateinit var profileRepository: ProfileRepository

//    @KafkaListener(topics = ["authorization_check"], groupId = "group1")
//    @SendTo("/")
//    fun listener(
//        @Payload token: String?
//    ) {
//        if (token != null) {
//            profileRepository
//                .findByToken(token)
//                ?.let {
////                    Response authorization successful
//                } ?: run {
////                    Response authorization failure
//            }
//        }
//    }
}