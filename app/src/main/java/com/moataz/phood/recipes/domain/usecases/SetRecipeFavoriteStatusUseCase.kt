package com.moataz.phood.recipes.domain.usecases

import com.moataz.phood.recipes.domain.repository.RecipesRepository
import javax.inject.Inject

class SetRecipeFavoriteStatusUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository,
) {
    suspend operator fun invoke(id: String, isFavorite: Boolean) {
        recipesRepository.setRecipeFavoriteStatus(id, isFavorite)
    }
}
