package com.phood.recipes.ui.viewmodel.model

data class RecipesFavouritesUIState(
    val isLoading: Boolean = true,
    val isSuccessful: Boolean = false,
    val isError: Boolean? = false,
    val recipes: List<RecipeUI> = emptyList(),
)
