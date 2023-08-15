package com.moataz.phood.recipes.domain.usecases

import com.moataz.phood.recipes.domain.entities.Recipe
import com.moataz.phood.recipes.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipesFavouritesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository,
) {
    operator fun invoke(): Flow<List<Recipe>> {
        return recipesRepository.getRecipesFavourites()
    }
}
