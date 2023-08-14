package com.moataz.phood.recipes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moataz.phood.recipes.data.local.utils.EntityConstant.RECIPE_TABLE

@Entity(tableName = RECIPE_TABLE)
data class RecipeEntity(
    @PrimaryKey
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
    val recipeType: String,
    val isFavorite: Boolean = false,
)
