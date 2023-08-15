package com.moataz.phood.recipes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.phood.recipes.domain.usecases.GetRecipesByCategoriesUseCase
import com.moataz.phood.recipes.ui.viewmodel.enums.RecipesTypes
import com.moataz.phood.recipes.ui.viewmodel.mapper.toRecipesUIModel
import com.moataz.phood.recipes.ui.viewmodel.model.RecipesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesByCategoriesUseCase,
) : ViewModel(), RecipesClicksListener {

    private val _recipesUiState = MutableStateFlow(RecipesUIState())
    val recipesUiState get() = _recipesUiState.asStateFlow()

    private val currentRecipeType = MutableStateFlow(RecipesTypes.ALL)

    private val _onRecipeClicked = Channel<Boolean>()
    val onRecipeClicked get() = _onRecipeClicked.receiveAsFlow()

    private val _onRecipesFavouritesClicked = Channel<Boolean>()
    val onRecipesFavouritesClicked get() = _onRecipesFavouritesClicked.receiveAsFlow()

    private val _onRecipesSearchClicked = Channel<Boolean>()
    val onRecipesSearchClicked get() = _onRecipesSearchClicked.receiveAsFlow()

    private val _recipeId = MutableStateFlow("")
    val recipeId get() = _recipeId.asStateFlow()

    init {
        getRecipes()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getRecipes() {
        viewModelScope.launch {
            try {
                currentRecipeType.flatMapLatest { recipeType ->
                    getRecipesUseCase(recipeType.pathName)
                }.collectLatest { recipesModel ->
                    _recipesUiState.update { recipesUiState ->
                        recipesUiState.copy(
                            isLoading = false,
                            isSuccessful = true,
                            isError = false,
                            recipes = recipesModel.toRecipesUIModel(),
                        )
                    }
                }
            } catch (e: Exception) {
                _recipesUiState.update { recipesUiState ->
                    recipesUiState.copy(
                        isLoading = false,
                        isSuccessful = false,
                        isError = true,
                        recipes = emptyList(),
                    )
                }
            }
        }
    }

    fun onChipTypeClicked(recipeType: RecipesTypes) {
        if (recipeType != currentRecipeType.value) {
            currentRecipeType.value = recipeType
        }
    }

    fun onRecipesFavouritesClicked() {
        viewModelScope.launch {
            _onRecipesFavouritesClicked.send(true)
        }
    }

    fun onRecipesSearchClicked() {
        viewModelScope.launch {
            _onRecipesSearchClicked.send(true)
        }
    }

    override fun onRecipeClicked(recipeId: String) {
        viewModelScope.launch {
            _onRecipeClicked.send(true)
            _recipeId.value = recipeId
        }
    }
}
