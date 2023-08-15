package com.moataz.phood.recipes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moataz.phood.recipes.domain.usecases.GetSearchRecipesUseCase
import com.moataz.phood.recipes.ui.viewmodel.mapper.toRecipesUIModel
import com.moataz.phood.recipes.ui.viewmodel.model.RecipesSearchUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesSearchViewModel @Inject constructor(
    private val getSearchRecipesUseCase: GetSearchRecipesUseCase,
) : ViewModel(), RecipesClicksListener {

    private val _recipesSearchUiState = MutableStateFlow(RecipesSearchUIState())
    val recipesSearchUiState get() = _recipesSearchUiState.asStateFlow()

    private val _isBackClicked = MutableStateFlow(false)
    val isBackClicked get() = _isBackClicked.asStateFlow()

    private val _onRecipeClicked = Channel<Boolean>()
    val onRecipeClicked get() = _onRecipeClicked.receiveAsFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery get() = _searchQuery.asStateFlow()

    private val _recipeId = MutableStateFlow("")
    val recipeId get() = _recipeId.asStateFlow()

    private var searchJob: Job? = null

    init {
        searchRecipes()
    }

    private fun searchRecipes() {
        viewModelScope.launch {
            _searchQuery.collectLatest { query ->
                if (query.isNotEmpty()) {
                    searchJob?.cancel()
                    searchJob = launchSearch(query)
                } else {
                    _recipesSearchUiState.update { it.copy(recipes = emptyList()) }
                }
            }
        }
    }

    private fun launchSearch(query: String): Job {
        return viewModelScope.launch {
            getSearchRecipesUseCase(query).collectLatest { recipes ->
                _recipesSearchUiState.update { it.copy(recipes = recipes.toRecipesUIModel()) }
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun onBackClicked() {
        _isBackClicked.value = true
    }

    override fun onRecipeClicked(recipeId: String) {
        viewModelScope.launch {
            _onRecipeClicked.send(true)
            _recipeId.value = recipeId
        }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }
}
