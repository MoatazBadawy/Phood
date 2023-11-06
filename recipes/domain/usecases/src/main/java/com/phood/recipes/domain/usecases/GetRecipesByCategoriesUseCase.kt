package com.phood.recipes.domain.usecases

import com.phood.recipes.domain.entities.Recipe
import com.phood.recipes.domain.repository.RecipesRepository
import com.phood.recipes.domain.usecases.enums.RecipesTypes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetRecipesByCategoriesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository,
) {
    suspend operator fun invoke(category: String): Flow<List<Recipe>> {
        recipesRepository.cachingRecipes()
        return if (category == RecipesTypes.ALL.pathName) {
            recipesRepository.getAllRecipesTypesFromLocal()
        } else {
            recipesRepository.getRecipesByCategoryFromLocal(category).map { recipes ->
                recipes.filter { recipe -> recipe.products.first() == category }
            }
        }
    }
}
