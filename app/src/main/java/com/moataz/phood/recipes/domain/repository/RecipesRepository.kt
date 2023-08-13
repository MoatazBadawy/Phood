package com.moataz.phood.recipes.domain.repository

import com.moataz.phood.recipes.domain.entities.Recipe

interface RecipesRepository {
    suspend fun getRecipes(): List<Recipe>
}
