package com.moataz.phood.app.di

import com.moataz.phood.identity.data.remote.IdentityService
import com.moataz.phood.identity.data.remote.IdentityServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideIdentityService(): IdentityService {
        return IdentityServiceImpl()
    }
}
