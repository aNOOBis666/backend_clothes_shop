package com.dlegin.profile.profile.models.mapping

import com.dlegin.profile.profile.models.ProfileVO
import com.dlegin.profile.profile.models.RegisterVO
import java.util.UUID

fun RegisterVO.toProfileVO() = ProfileVO(
    id = UUID.randomUUID().toString(),
    displayName = displayName,
    login = login,
    password = password,
    token = UUID.randomUUID().toString()
)