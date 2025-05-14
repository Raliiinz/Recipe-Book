package com.example.recipebook.domain.usecase

import com.example.recipebook.domain.di.qualifies.IoDispatchers
import com.example.recipebook.domain.model.RecipeDetailsModel
import com.example.recipebook.domain.repository.RecipeDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRecipeDetailsUseCase @Inject constructor(
    private val recipesRepository: RecipeDetailsRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(recipeId: Int): RecipeDetailsModel {
        return withContext(dispatcher) {
            recipesRepository.getRecipeDetails(recipeId)
        }
    }
}
