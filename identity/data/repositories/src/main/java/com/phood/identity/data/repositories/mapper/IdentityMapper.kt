package com.phood.identity.data.repositories.mapper

import com.phood.identity.data.remote.dto.UserDTO
import com.phood.identity.domain.entities.User

internal fun UserDTO.toUser(): User {
    return User(
        id = id,
        email = email,
        password = password,
    )
}
