package com.moataz.phood.di

import com.phood.identity.data.repositories.IdentityRepositoryImpl
import com.phood.identity.domain.repository.IdentityRepository
import com.phood.recipes.data.repositories.RecipesRepositoryImpl
import com.phood.recipes.domain.repository.RecipesRepository
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

    @ViewModelScoped
    @Binds
    abstract fun bindRecipesRepository(
        recipesRepositoryImpl: RecipesRepositoryImpl,
    ): RecipesRepository
}
