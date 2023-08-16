package com.phood.recipes.domain.usecases

import com.phood.recipes.domain.entities.Recipe
import com.phood.recipes.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipesFavouritesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository,
) {
    operator fun invoke(): Flow<List<Recipe>> {
        return recipesRepository.getRecipesFavourites()
    }
}
