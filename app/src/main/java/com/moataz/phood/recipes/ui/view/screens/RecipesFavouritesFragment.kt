package com.moataz.phood.recipes.ui.view.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.moataz.phood.R
import com.moataz.phood.databinding.FragmentRecipesFavouritesBinding
import com.moataz.phood.recipes.ui.view.adapters.RecipesAdapter
import com.moataz.phood.recipes.ui.viewmodel.RecipesFavouritesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFavouritesFragment : Fragment() {
    private val viewModel: RecipesFavouritesViewModel by viewModels()
    private lateinit var recipesAdapter: RecipesAdapter
    private lateinit var binding: FragmentRecipesFavouritesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_recipes_favourites,
            container,
            false,
        )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeEvents()
    }

    private fun initRecyclerView() {
        recipesAdapter = RecipesAdapter(emptyList(), viewModel)
        binding.recipesRecyclerView.adapter = recipesAdapter
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            viewModel.recipesFavouritesUiState.collect { recipesFavouritesUiState ->
                recipesAdapter.setItems(recipesFavouritesUiState.recipes)
                binding.recipesRecyclerView.scrollToPosition(0)
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.onRecipeClicked.collect {
                    if (it) {
                        viewModel.recipeId.collect { recipeId ->
                            navigateToDetailsScreen(recipeId)
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isBackClicked.collect {
                    if (it) {
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun navigateToDetailsScreen(recipeId: String) {
        findNavController().navigate(
            RecipesFavouritesFragmentDirections.actionRecipesFavouritesFragmentToRecipeDetailsFragment(
                recipeId,
            ),
        )
    }
}
