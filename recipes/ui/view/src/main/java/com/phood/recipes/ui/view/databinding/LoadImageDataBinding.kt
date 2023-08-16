package com.phood.recipes.ui.view.databinding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun View.loadImage(imageUrl: String?) {
    val image: ImageView = this as ImageView
    image.load(imageUrl)
}
