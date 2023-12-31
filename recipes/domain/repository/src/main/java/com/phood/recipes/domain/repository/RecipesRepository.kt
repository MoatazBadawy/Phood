package com.phood.recipes.domain.repository

import com.phood.recipes.domain.entities.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    suspend fun getRecipesFromRemote(): List<Recipe>
    suspend fun insertRecipe()
    suspend fun deleteRecipe()
    suspend fun cachingRecipes()
    fun getRecipesByCategoryFromLocal(recipeType: String): Flow<List<Recipe>>
    fun getAllRecipesTypesFromLocal(): Flow<List<Recipe>>
    fun getRecipeDetailsById(id: String): Flow<Recipe>
    suspend fun setRecipeFavoriteStatus(id: String, isFavorite: Boolean)
    fun getRecipesFavourites(): Flow<List<Recipe>>
    fun searchRecipes(): Flow<List<Recipe>>
}
