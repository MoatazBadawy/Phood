package com.moataz.phood.app.di

import com.moataz.phood.identity.data.repositories.IdentityRepositoryImpl
import com.moataz.phood.identity.domain.repository.IdentityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @ViewModelScoped
    @Binds
    abstract fun bindIdentityRepository(
        identityRepositoryImpl: IdentityRepositoryImpl,
    ): IdentityRepository
}
