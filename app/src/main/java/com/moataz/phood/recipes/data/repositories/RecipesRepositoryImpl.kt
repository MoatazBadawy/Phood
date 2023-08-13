package com.moataz.phood.recipes.data.repositories

import com.moataz.phood.recipes.data.remote.RecipesService
import com.moataz.phood.recipes.data.repositories.mapper.toRecipes
import com.moataz.phood.recipes.domain.entities.Recipe
import com.moataz.phood.recipes.domain.repository.RecipesRepository
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val recipesRemoteService: RecipesService,
) : RecipesRepository {
    override suspend fun getRecipes(): List<Recipe> {
        return recipesRemoteService.getRecipes().toRecipes()
    }
}
