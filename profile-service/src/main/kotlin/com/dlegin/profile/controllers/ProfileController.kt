package com.dlegin.profile.controllers

import com.dlegin.profile.profile.models.AuthVO
import com.dlegin.profile.profile.models.ProfileVO
import com.dlegin.profile.profile.models.RegisterVO
import com.dlegin.profile.profile.models.TokenVO
import com.dlegin.profile.profile.repository.*
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.net.URISyntaxException

@RestController
@RequestMapping("/api/v1")
class ProfileController {

    companion object {
        private const val LOGIN = "/login"
        private const val REGISTER = "/register"

        private const val PROFILE = "/profile"

        private const val ALL_USERS = "/all_users"
    }

    @Autowired
    lateinit var profileRepository: ProfileRepository

    @RequestMapping(
        LOGIN,
        method = [RequestMethod.GET],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Transactional
    @ResponseBody
    @Throws(URISyntaxException::class)
    fun loginProfile(@RequestBody authVO: AuthVO): ResponseEntity<TokenVO> {
        val result = profileRepository.login(authVO)

        return ResponseEntity.ok(result)
    }

    @RequestMapping(
        REGISTER,
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Transactional
    @Throws(URISyntaxException::class)
    @ResponseBody
    fun registerProfile(@RequestBody registerVO: RegisterVO): ResponseEntity<TokenVO> {
        val result = profileRepository.createUser(registerVO)

        return ResponseEntity.ok(result)
    }

    @RequestMapping(
        PROFILE,
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Transactional
    @Throws(URISyntaxException::class)
    @ResponseBody
    fun getProfile(@RequestHeader("Authorization") token: String): ResponseEntity<ProfileVO> {
        val result = profileRepository.getProfile(token)

        return ResponseEntity.ok(result)
    }

    @RequestMapping(
        PROFILE,
        method = [RequestMethod.PUT],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @SendTo("/profile/socket")
    @Transactional
    @Throws(URISyntaxException::class)
    @ResponseBody
    fun editProfile(
        @RequestHeader("Authorization") token: String,
        @RequestBody profileVO: ProfileVO
    ): ResponseEntity<ProfileVO> {
        val result = profileRepository.updateProfile(token, profileVO)

        return ResponseEntity.ok(result)
    }

    @RequestMapping(
        ALL_USERS,
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Transactional
    @Throws(URISyntaxException::class)
    @ResponseBody
    fun allUsers(): ResponseEntity<List<ProfileVO>> {
        val result = profileRepository.getProfiles()

        return ResponseEntity.ok(result)
    }
}