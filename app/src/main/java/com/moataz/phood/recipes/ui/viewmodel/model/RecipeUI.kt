package com.moataz.phood.recipes.ui.viewmodel.model

data class RecipeUI(
    val id: String,
    val name: String,
    val image: String,
    val difficulty: Int,
    val headline: String,
    val fats: String,
    val carbos: String,
    val calories: String,
    val proteins: String,
    val description: String,
    val time: String,
    val ingredients: List<String>,
)