package com.moataz.phood.identity.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.moataz.phood.identity.data.local.utils.DataStorePreferencesKeys.LOGIN_STATE
import com.moataz.phood.identity.data.repositories.datasources.IdentityLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IdentityLocalDataSourceImpl @Inject constructor(
    private val userDataStore: DataStore<Preferences>,
) : IdentityLocalDataSource {
    override suspend fun saveLoggedInStatus(status: Boolean) {
        withContext(Dispatchers.IO) {
            userDataStore.edit { preferences ->
                preferences[booleanPreferencesKey(LOGIN_STATE)] = status
            }
        }
    }

    override suspend fun getLoggedInStatus(): Boolean? {
        return withContext(Dispatchers.IO) {
            userDataStore.data.map { preferences ->
                preferences[booleanPreferencesKey(LOGIN_STATE)]
            }.first()
        }
    }
}
