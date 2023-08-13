package com.moataz.phood.recipes.data.repositories.mapper

import com.moataz.phood.recipes.data.remote.dto.RecipeDTO
import com.moataz.phood.recipes.domain.entities.Recipe

internal fun RecipeDTO.toRecipe(): Recipe {
    return Recipe(
        id = id ?: "",
        name = name ?: "",
        image = image ?: "",
        difficulty = difficulty ?: 0,
        headline = headline ?: "",
        fats = fats ?: "",
        carbos = carbos ?: "",
        calories = calories ?: "",
        proteins = proteins ?: "",
        description = description ?: "",
        time = time ?: "",
        ingredients = ingredients ?: emptyList(),
    )
}

internal fun List<RecipeDTO>.toRecipes(): List<Recipe> {
    return map { recipeDTO -> recipeDTO.toRecipe() }
}
