package com.moataz.phood.identity.domain.repository

import com.moataz.phood.identity.domain.entities.User

interface IdentityRepository {
    suspend fun signIn(email: String, password: String): User
    suspend fun saveLoggedInStatus(status: Boolean)
    suspend fun getLoggedInStatus(): Boolean?
}
