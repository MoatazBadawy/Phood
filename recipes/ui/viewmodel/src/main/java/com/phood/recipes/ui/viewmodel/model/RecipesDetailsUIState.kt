package com.phood.recipes.ui.viewmodel.model

data class RecipesDetailsUIState(
    val isLoading: Boolean = true,
    val isSuccessful: Boolean = false,
    val isError: Boolean? = false,
    val recipe: RecipeUI = RecipeUI(),
)
