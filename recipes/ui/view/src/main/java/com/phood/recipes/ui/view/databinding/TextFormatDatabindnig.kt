package com.phood.recipes.ui.view.databinding

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("formattedIngredients")
fun TextView.setFormattedIngredients(ingredients: List<String>?) {
    ingredients?.let {
        val formattedText = ingredients.joinToString("\n")
        text = formattedText
    }
}

@BindingAdapter("caloriesText")
fun setCaloriesText(textView: TextView, calories: String?) {
    calories?.let {
        val caloriesWithoutUnit = it.replace(" kcal", "")
        textView.text = caloriesWithoutUnit
    }
}
