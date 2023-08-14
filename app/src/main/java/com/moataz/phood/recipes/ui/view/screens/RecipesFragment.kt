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
import com.moataz.phood.databinding.FragmentRecipesBinding
import com.moataz.phood.recipes.ui.view.adapters.RecipesAdapter
import com.moataz.phood.recipes.ui.viewmodel.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : Fragment() {
    private val viewModel: RecipesViewModel by viewModels()
    private lateinit var recipesAdapter: RecipesAdapter
    private lateinit var binding: FragmentRecipesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_recipes,
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
            viewModel.recipesUiState.collect { recipesUIState ->
                recipesAdapter.setItems(recipesUIState.recipes)
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
    }

    private fun navigateToDetailsScreen(recipeId: String) {
        findNavController().navigate(
            RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment(recipeId),
        )
    }
}
