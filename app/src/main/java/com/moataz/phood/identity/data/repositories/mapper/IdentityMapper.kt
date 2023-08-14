package com.moataz.phood.identity.data.repositories.mapper

import com.moataz.phood.identity.data.remote.dto.UserDTO
import com.moataz.phood.identity.domain.entities.User

internal fun UserDTO.toUser(): User {
    return User(
        id = id,
        email = email,
        password = password,
    )
}
