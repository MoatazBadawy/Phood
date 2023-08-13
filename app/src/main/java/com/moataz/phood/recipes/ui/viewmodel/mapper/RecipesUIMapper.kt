package com.moataz.phood.recipes.ui.viewmodel.mapper

import com.moataz.phood.recipes.domain.entities.Recipe
import com.moataz.phood.recipes.ui.viewmodel.model.RecipeUI

internal fun Recipe.toRecipeUIModel(): RecipeUI {
    return RecipeUI(
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
    )
}

internal fun List<Recipe>.toRecipesUIModel(): List<RecipeUI> {
    return map { recipeUI -> recipeUI.toRecipeUIModel() }
}
