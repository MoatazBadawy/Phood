package com.moataz.phood.recipes.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.phood.recipes.domain.usecases.GetRecipeDetailsByIdUseCase
import com.moataz.phood.recipes.domain.usecases.SetRecipeFavoriteStatusUseCase
import com.moataz.phood.recipes.ui.view.screens.RecipeDetailsFragmentArgs
import com.moataz.phood.recipes.ui.viewmodel.mapper.toRecipeUIModel
import com.moataz.phood.recipes.ui.viewmodel.model.RecipeUI
import com.moataz.phood.recipes.ui.viewmodel.model.RecipesDetailsUIState
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
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipesDetailsUseCase: GetRecipeDetailsByIdUseCase,
    private val setRecipeFavoriteStatusUseCase: SetRecipeFavoriteStatusUseCase,
    state: SavedStateHandle,
) : ViewModel() {

    private val recipe = RecipeDetailsFragmentArgs.fromSavedStateHandle(state)

    private val _recipeDetailsUiState = MutableStateFlow(RecipesDetailsUIState())
    val recipeDetailsUiState get() = _recipeDetailsUiState.asStateFlow()

    private val _isBackClicked = Channel<Boolean>()
    val isBackClicked get() = _isBackClicked.receiveAsFlow()

    init {
        getRecipeDetails()
    }

    private fun getRecipeDetails() {
        viewModelScope.launch {
            try {
                getRecipesDetailsUseCase(recipe.recipeId).collectLatest { recipe ->
                    _recipeDetailsUiState.update { recipeDetailsUiState ->
                        recipeDetailsUiState.copy(
                            isLoading = false,
                            isSuccessful = true,
                            isError = false,
                            recipe = recipe.toRecipeUIModel(),
                        )
                    }
                }
            } catch (e: Exception) {
                _recipeDetailsUiState.update { recipeDetailsUiState ->
                    recipeDetailsUiState.copy(
                        isLoading = false,
                        isSuccessful = false,
                        isError = true,
                        recipe = RecipeUI(),
                    )
                }
            }
        }
    }

    fun updateRecipeFavorite(id: String) {
        viewModelScope.launch {
            setRecipeFavoriteStatusUseCase(
                id,
                isFavorite = !recipeDetailsUiState.value.recipe.isFavorite,
            )
        }
    }

    fun onBackClicked() {
        viewModelScope.launch {
            _isBackClicked.send(true)
        }
    }
}
