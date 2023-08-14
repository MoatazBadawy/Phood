package com.moataz.phood.identity.data.remote

import com.moataz.phood.identity.data.remote.dto.UserDTO

interface IdentityService {
    suspend fun signIn(email: String, password: String): UserDTO
}
