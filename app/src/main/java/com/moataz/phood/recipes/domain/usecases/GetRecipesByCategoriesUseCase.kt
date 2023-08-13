package com.moataz.phood.recipes.domain.usecases

import com.moataz.phood.recipes.domain.entities.Recipe
import com.moataz.phood.recipes.domain.repository.RecipesRepository
import com.moataz.phood.recipes.domain.usecases.enums.RecipesTypes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRecipesByCategoriesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository,
) {
    operator fun invoke(category: String): Flow<List<Recipe>> {
        return flow {
            if (category == RecipesTypes.ALL.pathName) {
                emit(recipesRepository.getRecipes())
            } else {
                emit(
                    recipesRepository.getRecipes().filter { recipe ->
                        recipe.products.first() == category
                    },
                )
            }
        }
    }
}
