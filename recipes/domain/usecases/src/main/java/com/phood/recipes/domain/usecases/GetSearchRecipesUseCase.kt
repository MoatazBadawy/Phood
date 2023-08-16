package com.phood.recipes.domain.usecases

import com.phood.recipes.domain.entities.Recipe
import com.phood.recipes.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository,
) {
    operator fun invoke(searchQuery: String): Flow<List<Recipe>> {
        return recipesRepository.searchRecipes(searchQuery)
    }
}
