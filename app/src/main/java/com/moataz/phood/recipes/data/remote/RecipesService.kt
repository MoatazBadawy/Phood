package com.moataz.phood.recipes.data.remote

import com.moataz.phood.recipes.data.remote.dto.RecipeDTO
import retrofit2.http.GET

interface RecipesService {
    @GET("43427003d33f1f6b51cc")
    suspend fun getRecipes(): List<RecipeDTO>
}
