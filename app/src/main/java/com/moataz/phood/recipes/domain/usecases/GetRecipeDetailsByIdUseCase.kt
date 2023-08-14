package com.moataz.phood.recipes.domain.usecases

import com.moataz.phood.recipes.domain.entities.Recipe
import com.moataz.phood.recipes.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeDetailsByIdUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository,
) {
    suspend operator fun invoke(id: String): Flow<Recipe> {
        return recipesRepository.getRecipeDetailsById(id)
    }
}
