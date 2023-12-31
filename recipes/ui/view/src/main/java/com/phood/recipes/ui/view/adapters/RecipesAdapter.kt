package com.phood.recipes.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.phood.recipes.ui.view.R
import com.phood.recipes.ui.view.databinding.ItemRecipeBinding
import com.phood.recipes.ui.viewmodel.RecipesClicksListener
import com.phood.recipes.ui.viewmodel.model.RecipeUI

class RecipesAdapter(
    private var recipes: List<RecipeUI>,
    private val viewModel: RecipesClicksListener,
) : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    fun setItems(newItems: List<RecipeUI>) {
        val diffUtilResult =
            DiffUtil.calculateDiff(RecipesDiffUtil(recipes, newItems, ::areContentsTheSame))
        recipes = newItems
        diffUtilResult.dispatchUpdatesTo(this)
    }

    private fun areContentsTheSame(oldItem: RecipeUI, newItem: RecipeUI): Boolean {
        return oldItem == newItem
    }

    override fun getItemCount(): Int = recipes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recipe,
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.binding.run {
            recipe = recipes[position]
            listener = viewModel
        }
    }

    inner class RecipesViewHolder(val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root)
}
