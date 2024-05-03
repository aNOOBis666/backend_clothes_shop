package com.dlegin.profile.controllers

import com.dlegin.profile.profile.models.AuthVO
import com.dlegin.profile.profile.models.ProfileVO
import com.dlegin.profile.profile.models.RegisterVO
import com.dlegin.profile.profile.repository.ProfileRepository
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.net.URISyntaxException

@RestController
@RequestMapping("/api/v1")
class AuthorizationController {

    companion object {
        private const val LOGIN = "/login"
        private const val REGISTER = "/register"

        private const val PROFILE = "/profile"

        private const val ALL_USERS = "/all_users"
    }

    @Autowired
    lateinit var profileRepository: ProfileRepository

    private val gson = Gson()

    @RequestMapping(
        LOGIN,
        method = [RequestMethod.GET],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Transactional
    @ResponseBody
    @Throws(URISyntaxException::class)
    fun loginProfile(@RequestBody authVO: String): ResponseEntity<String> {
        val auth = gson.fromJson(authVO, AuthVO::class.java)
        val result = profileRepository.login(auth)

        return ResponseEntity.created(URI("/api/v1/login/$result")).body(gson.toJson(result))
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
    fun registerProfile(@RequestBody registerVO: String): ResponseEntity<String> {
        val register = gson.fromJson(registerVO, RegisterVO::class.java)
        val result = profileRepository.register(register)

        return ResponseEntity.created(URI("/api/v1/login/$result")).body(gson.toJson(result))
    }

    @RequestMapping(
        PROFILE,
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Transactional
    @Throws(URISyntaxException::class)
    @ResponseBody
    fun getProfile(@RequestHeader("Authorization") token: String): ResponseEntity<String> {
        val result = profileRepository.profileMe(token)

        return ResponseEntity.created(URI("/api/v1/login/" + result?.id)).body(gson.toJson(result))
    }

    @RequestMapping(
        PROFILE,
        method = [RequestMethod.PUT],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Transactional
    @Throws(URISyntaxException::class)
    @ResponseBody
    fun editProfile(
        @RequestHeader("Authorization") token: String,
        @RequestBody profileVO: String
    ): ResponseEntity<String> {
        val profileMe = gson.fromJson(profileVO, ProfileVO::class.java)
        val result = profileRepository.updateProfileMe(token, profileMe)

        return ResponseEntity.created(URI("/api/v1/login/" + result?.id)).body(gson.toJson(result))
    }

    @RequestMapping(
        ALL_USERS,
        method = [RequestMethod.PUT],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Transactional
    @Throws(URISyntaxException::class)
    @ResponseBody
    fun allUsers(): ResponseEntity<String> {
        val result = profileRepository.allUsers()

        return ResponseEntity.created(URI("/api/v1/login/$result")).body(gson.toJson(result))
    }
}