package com.phood.recipes.domain.usecases

import com.phood.recipes.domain.entities.Recipe
import com.phood.recipes.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeDetailsByIdUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository,
) {
    operator fun invoke(id: String): Flow<Recipe> {
        return recipesRepository.getRecipeDetailsById(id)
    }
}
