package com.moataz.phood.recipes.ui.view.databinding

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("showIfTrue")
fun View.showIfTrue(condition: Boolean) {
    this.isVisible = condition
}
