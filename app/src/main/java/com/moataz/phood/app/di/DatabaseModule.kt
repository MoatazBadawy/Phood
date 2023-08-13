package com.moataz.phood.app.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideIdentityDataStorePreferences(
        @ApplicationContext applicationContext: Context,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            applicationContext.preferencesDataStoreFile("com.moataz.phood.app_preferences")
        }
    }
}
