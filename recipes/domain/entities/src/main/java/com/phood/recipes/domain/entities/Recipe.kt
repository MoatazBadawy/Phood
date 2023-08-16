package com.phood.recipes.domain.entities

data class Recipe(
    val id: String,
    val name: String,
    val image: String,
    val headline: String,
    val fats: String,
    val carbos: String,
    val calories: String,
    val proteins: String,
    val description: String,
    val time: String,
    val ingredients: List<String>,
    val products: List<String>,
    val isFavorite: Boolean,
)
