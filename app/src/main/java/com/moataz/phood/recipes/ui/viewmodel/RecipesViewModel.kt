package com.moataz.phood.recipes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.phood.recipes.domain.usecases.GetRecipesUseCase
import com.moataz.phood.recipes.ui.viewmodel.mapper.toRecipesUIModel
import com.moataz.phood.recipes.ui.viewmodel.model.RecipesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
) : ViewModel() {

    private val _recipesUiState = MutableStateFlow(RecipesUIState())
    val recipesUiState get() = _recipesUiState.asStateFlow()

    init {
        getRecipes()
    }

    fun getRecipes() {
        viewModelScope.launch {
            try {
                _recipesUiState.update { recipesState ->
                    recipesState.copy(
                        recipes = getRecipesUseCase().toRecipesUIModel(),
                        isSuccessful = true,
                        isLoading = false,
                        isError = false,
                    )
                }
            } catch (e: Exception) {
                _recipesUiState.update { recipesState ->
                    recipesState.copy(
                        isError = true,
                        isLoading = false,
                        isSuccessful = false,
                        recipes = emptyList(),
                    )
                }
            }
        }
    }
}
