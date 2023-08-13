package com.moataz.phood.recipes.domain.usecases

import com.moataz.phood.recipes.domain.entities.Recipe
import com.moataz.phood.recipes.domain.repository.RecipesRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository,
) {
    suspend operator fun invoke(): List<Recipe> {
        return recipesRepository.getRecipes()
    }
}
