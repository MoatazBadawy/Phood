package com.moataz.phood.recipes.data.repositories.mapper

import com.moataz.phood.recipes.data.local.entity.RecipeEntity
import com.moataz.phood.recipes.data.remote.dto.RecipeDTO
import com.moataz.phood.recipes.domain.entities.Recipe

internal fun List<RecipeDTO>.toRecipesRemote(): List<Recipe> {
    return map { recipeDTO -> recipeDTO.toRecipe() }
}

internal fun RecipeDTO.toRecipe(): Recipe {
    return Recipe(
        id = id ?: "",
        name = name ?: "",
        image = image ?: "",
        headline = headline ?: "",
        fats = fats ?: "",
        carbos = carbos ?: "",
        calories = calories ?: "",
        proteins = proteins ?: "",
        description = description ?: "",
        time = time ?: "",
        ingredients = ingredients ?: emptyList(),
        products = products ?: emptyList(),
        isFavorite = false,
    )
}

internal fun List<RecipeEntity>.toRecipesLocal(): List<Recipe> {
    return map { recipeDTO -> recipeDTO.toRecipeLocal() }
}

internal fun RecipeEntity.toRecipeLocal(): Recipe {
    return Recipe(
        id = id,
        name = name,
        image = image,
        headline = headline,
        fats = fats,
        carbos = carbos,
        calories = calories,
        proteins = proteins,
        description = description,
        time = time,
        ingredients = ingredients,
        products = listOf(recipeType),
        isFavorite = isFavorite,
    )
}

internal fun RecipeDTO.toRecipeEntity(): RecipeEntity {
    return RecipeEntity(
        id = id ?: "",
        name = name ?: "",
        image = image ?: "",
        headline = headline ?: "",
        fats = fats ?: "",
        carbos = carbos ?: "",
        calories = calories ?: "",
        proteins = proteins ?: "",
        description = description ?: "",
        time = time ?: "",
        ingredients = ingredients ?: emptyList(),
        recipeType = products?.first() ?: "",
    )
}
