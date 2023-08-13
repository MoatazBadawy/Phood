package com.moataz.phood.recipes.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RecipeDTO(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("difficulty")
    val difficulty: Int?,
    @SerializedName("headline")
    val headline: String?,
    @SerializedName("fats")
    val fats: String?,
    @SerializedName("carbos")
    val carbos: String?,
    @SerializedName("calories")
    val calories: String?,
    @SerializedName("proteins")
    val proteins: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("ingredients")
    val ingredients: List<String>?,
)
