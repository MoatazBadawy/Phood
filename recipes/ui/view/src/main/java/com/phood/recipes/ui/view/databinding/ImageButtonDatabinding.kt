package com.phood.recipes.ui.view.databinding

import android.widget.ImageButton
import androidx.databinding.BindingAdapter
import com.phood.recipes.ui.view.R

@BindingAdapter("changeFavoriteIcon")
fun ImageButton.changeFavoriteIcon(isFavourite: Boolean) {
    if (isFavourite) {
        setImageResource(R.drawable.ic_favorite_red)
    } else {
        setImageResource(R.drawable.ic_favorite_black)
    }
}
