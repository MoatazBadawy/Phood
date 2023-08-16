package com.phood.identity.data.repositories

import com.phood.identity.data.remote.IdentityService
import com.phood.identity.data.repositories.datasources.IdentityLocalDataSource
import com.phood.identity.data.repositories.mapper.toUser
import com.phood.identity.domain.entities.User
import com.phood.identity.domain.repository.IdentityRepository
import javax.inject.Inject

class IdentityRepositoryImpl @Inject constructor(
    private val remoteService: IdentityService,
    private val localDataSource: IdentityLocalDataSource,
) : IdentityRepository {

    override suspend fun signIn(email: String, password: String): User {
        return remoteService.signIn(email, password).toUser()
    }

    override suspend fun saveLoggedInStatus(status: Boolean) {
        localDataSource.saveLoggedInStatus(status)
    }

    override suspend fun getLoggedInStatus(): Boolean? {
        return localDataSource.getLoggedInStatus()
    }
}
