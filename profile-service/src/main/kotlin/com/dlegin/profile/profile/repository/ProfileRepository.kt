package com.dlegin.profile.profile.repository

import com.dlegin.profile.profile.models.*
import com.dlegin.profile.profile.models.mapping.toProfileVO
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProfileRepository : MongoRepository<ProfileVO, String>

fun ProfileRepository.login(authVO: AuthVO): TokenVO? {
    val profile = findByAuth(authVO)
    return (profile?.id?.let { id ->
        deleteById(id)
        save(
            profile.copy(
                token = UUID.randomUUID().toString()
            )
        )
    } ?: profile)?.token?.toToken()
}

private fun ProfileRepository.findByAuth(authVO: AuthVO): ProfileVO? {
    return findAll().firstOrNull {
        it.login == authVO.login && it.password == authVO.password
    }
}

private fun ProfileRepository.findByToken(token: String): ProfileVO? {
    return findAll().firstOrNull {
        it.token == token
    }
}

fun ProfileRepository.createUser(registerVO: RegisterVO): TokenVO? {
    val newUser = registerVO.toProfileVO()
    save(newUser)
    return newUser.token?.toToken()
}

fun ProfileRepository.getProfile(token: String): ProfileVO? {
    return findByToken(token)
}

fun ProfileRepository.updateProfile(token: String, profileVO: ProfileVO): ProfileVO? {
    val profile = findByToken(token)
    return profile?.id?.let { id ->
        val newProfile = profileVO.copy(id = id, token = profile.token)
        deleteById(id)
        save(newProfile)
    } ?: profile
}

fun ProfileRepository.getProfiles(): List<ProfileVO> {
    return findAll().map {
        val nonToken = it.copy(token = null)
        nonToken
    }
}