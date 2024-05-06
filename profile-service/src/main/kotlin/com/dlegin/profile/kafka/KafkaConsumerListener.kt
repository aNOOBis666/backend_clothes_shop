package com.dlegin.profile.kafka

import com.dlegin.profile.profile.repository.ProfileRepository
import com.dlegin.profile.profile.repository.findByToken
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Service


@Service
@Slf4j
class KafkaConsumerListener {
    @Autowired
    lateinit var profileRepository: ProfileRepository

    @KafkaListener(topics = ["authorization_check"], groupId = "group1")
    @SendTo("/profile/")
    fun listener(
        @Payload token: String?
    ): ResponseEntity<Boolean> {
        return if (token != null) {
            profileRepository
                .findByToken(token)
                ?.let {
//                    Response authorization successful
                    ResponseEntity.ok(true)
                } ?: run {
//                    Response authorization failure
                ResponseEntity.ok(false)
            }
        } else ResponseEntity.ok(false)
    }
}