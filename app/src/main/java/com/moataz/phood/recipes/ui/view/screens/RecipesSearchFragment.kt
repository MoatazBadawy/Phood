package com.moataz.phood.recipes.ui.view.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.moataz.phood.R
import com.moataz.phood.databinding.FragmentRecipesSearchBinding
import com.moataz.phood.recipes.ui.view.adapters.RecipesAdapter
import com.moataz.phood.recipes.ui.viewmodel.RecipesSearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesSearchFragment : Fragment() {
    private val viewModel: RecipesSearchViewModel by viewModels()
    private lateinit var recipesAdapter: RecipesAdapter
    private lateinit var binding: FragmentRecipesSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_recipes_search,
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
        openKeyboardWhenStarted()
    }

    private fun initRecyclerView() {
        recipesAdapter = RecipesAdapter(emptyList(), viewModel)
        binding.recipesRecyclerView.adapter = recipesAdapter
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            viewModel.recipesSearchUiState.collect { recipesSearchUiState ->
                recipesAdapter.setItems(recipesSearchUiState.recipes)
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
            RecipesSearchFragmentDirections.actionRecipesSearchFragmentToRecipeDetailsFragment(
                recipeId,
            ),
        )
    }

    private fun openKeyboardWhenStarted() {
        binding.searchEditText.requestFocus()
        val inputMethodManager =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.searchEditText, InputMethodManager.SHOW_IMPLICIT)
    }
}
