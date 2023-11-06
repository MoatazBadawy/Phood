package com.phood.identity.domain.repository

import com.phood.identity.domain.entities.User

interface IdentityRepository {
    suspend fun signIn(email: String, password: String): User
    suspend fun saveLoggedInStatus(status: Boolean)
    suspend fun getLoggedInStatus(): Boolean?
}
