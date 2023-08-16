package com.moataz.phood.di

import com.phood.identity.data.local.IdentityLocalDataSourceImpl
import com.phood.identity.data.repositories.datasources.IdentityLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindIdentityDataSource(
        identityLocalDataSourceImpl: IdentityLocalDataSourceImpl,
    ): IdentityLocalDataSource
}
