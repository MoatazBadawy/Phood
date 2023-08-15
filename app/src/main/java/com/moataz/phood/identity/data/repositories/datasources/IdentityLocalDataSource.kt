package com.moataz.phood.identity.data.repositories.datasources

interface IdentityLocalDataSource {
    suspend fun saveLoggedInStatus(status: Boolean)
    suspend fun getLoggedInStatus(): Boolean?
}
