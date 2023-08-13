package com.moataz.phood.recipes.ui.view.adapters

import androidx.recyclerview.widget.DiffUtil
import com.moataz.phood.recipes.ui.viewmodel.model.RecipeUI

class RecipesDiffUtil(
    private val oldList: List<RecipeUI>,
    private val newList: List<RecipeUI>,
    private val areContentsTheSame: (RecipeUI, RecipeUI) -> Boolean,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return areContentsTheSame(oldList[oldItemPosition], newList[newItemPosition])
    }
}
