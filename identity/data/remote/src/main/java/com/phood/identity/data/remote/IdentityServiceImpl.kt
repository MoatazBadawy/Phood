package com.phood.identity.data.remote

import com.phood.identity.data.remote.dto.UserDTO

class IdentityServiceImpl : IdentityService {
    override suspend fun signIn(email: String, password: String): UserDTO {
        return UserDTO()
    }
}
