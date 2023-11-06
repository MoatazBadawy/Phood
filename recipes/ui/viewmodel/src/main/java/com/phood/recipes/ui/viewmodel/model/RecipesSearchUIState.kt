package com.phood.recipes.ui.viewmodel.model

data class RecipesSearchUIState(
    val isLoading: Boolean = true,
    val isSuccessful: Boolean = false,
    val isError: Boolean? = false,
    val error: String = "",
    val recipes: List<RecipeUI> = emptyList(),
)
