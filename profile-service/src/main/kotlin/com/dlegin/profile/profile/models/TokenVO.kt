package com.dlegin.profile.profile.models

data class TokenVO(
    val token: String? = null
)

fun String.toToken() = TokenVO(token = this)