package com.moataz.phood.recipes.data.repositories

import com.moataz.phood.recipes.data.local.RecipesDao
import com.moataz.phood.recipes.data.remote.RecipesService
import com.moataz.phood.recipes.data.repositories.mapper.toRecipeEntity
import com.moataz.phood.recipes.data.repositories.mapper.toRecipeLocal
import com.moataz.phood.recipes.data.repositories.mapper.toRecipesLocal
import com.moataz.phood.recipes.data.repositories.mapper.toRecipesRemote
import com.moataz.phood.recipes.data.repositories.utils.NetworkHelper
import com.moataz.phood.recipes.domain.entities.Recipe
import com.moataz.phood.recipes.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val recipesRemoteService: RecipesService,
    private val recipesLocalDatabase: RecipesDao,
    private val networkHelper: NetworkHelper,
) : RecipesRepository {

    override suspend fun getRecipesFromRemote(): List<Recipe> {
        return recipesRemoteService.getRecipes().toRecipesRemote()
    }

    override suspend fun insertRecipe() {
        recipesRemoteService.getRecipes().map { recipeDTO ->
            recipesLocalDatabase.insertRecipe(recipeDTO.toRecipeEntity())
        }
    }

    override suspend fun deleteRecipe() {
        recipesLocalDatabase.deleteRecipe()
    }

    override suspend fun cachingRecipes() {
        if (recipesLocalDatabase.getRecipes().isEmpty()) {
            if (networkHelper.isInternetAvailable()) {
                getRecipesFromRemote()
                deleteRecipe()
                insertRecipe()
            }
        }
    }

    override fun getRecipesByCategoryFromLocal(recipeType: String): Flow<List<Recipe>> {
        return recipesLocalDatabase.getRecipesByCategory(recipeType).map { recipesEntities ->
            recipesEntities.toRecipesLocal()
        }
    }

    override fun getAllRecipesTypesFromLocal(): Flow<List<Recipe>> {
        return recipesLocalDatabase.getAllRecipes().map { recipesEntities ->
            recipesEntities.toRecipesLocal()
        }
    }

    override fun getRecipeDetailsById(id: String): Flow<Recipe> {
        return recipesLocalDatabase.getRecipeDetailsById(id).map { recipeEntity ->
            recipeEntity.toRecipeLocal()
        }
    }

    override suspend fun setRecipeFavoriteStatus(id: String, isFavorite: Boolean) {
        recipesLocalDatabase.setRecipeFavoriteStatus(id, isFavorite)
    }
}
