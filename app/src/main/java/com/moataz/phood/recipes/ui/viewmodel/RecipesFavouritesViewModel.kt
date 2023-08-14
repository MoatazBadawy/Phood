package com.moataz.phood.recipes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.phood.recipes.domain.usecases.GetRecipesFavouritesUseCase
import com.moataz.phood.recipes.ui.viewmodel.mapper.toRecipesUIModel
import com.moataz.phood.recipes.ui.viewmodel.model.RecipesFavouritesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesFavouritesViewModel @Inject constructor(
    private val getRecipesFavouritesUseCase: GetRecipesFavouritesUseCase,
) : ViewModel(), RecipesClicksListener {

    private val _recipesFavouritesUiState = MutableStateFlow(RecipesFavouritesUIState())
    val recipesFavouritesUiState get() = _recipesFavouritesUiState.asStateFlow()

    private val _onRecipeClicked = Channel<Boolean>()
    val onRecipeClicked get() = _onRecipeClicked.receiveAsFlow()

    private val _isBackClicked = Channel<Boolean>()
    val isBackClicked get() = _isBackClicked.receiveAsFlow()

    private val _recipeId = MutableStateFlow("")
    val recipeId get() = _recipeId.asStateFlow()

    init {
        getFavouritesRecipes()
    }

    private fun getFavouritesRecipes() {
        viewModelScope.launch {
            try {
                getRecipesFavouritesUseCase().collectLatest { recipes ->
                    _recipesFavouritesUiState.update { recipesFavouritesUiState ->
                        recipesFavouritesUiState.copy(
                            isLoading = false,
                            isSuccessful = true,
                            isError = false,
                            recipes = recipes.toRecipesUIModel(),
                        )
                    }
                }
            } catch (e: Exception) {
                _recipesFavouritesUiState.update { recipesUiFavouritesState ->
                    recipesUiFavouritesState.copy(
                        isLoading = false,
                        isSuccessful = false,
                        isError = true,
                        recipes = emptyList(),
                    )
                }
            }
        }
    }

    override fun onRecipeClicked(recipeId: String) {
        viewModelScope.launch {
            _onRecipeClicked.send(true)
            _recipeId.value = recipeId
        }
    }

    fun onBackClicked() {
        viewModelScope.launch {
            _isBackClicked.send(true)
        }
    }
}
