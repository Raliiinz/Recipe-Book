package com.example.recipebook.domain.usecase

import com.example.recipebook.domain.di.qualifies.IoDispatchers
import com.example.recipebook.domain.model.RecipeResult
import com.example.recipebook.domain.repository.RecipesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository,
    @IoDispatchers private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(ingredients: String): RecipeResult {
        return withContext(dispatcher) {
            recipesRepository.searchRecipes(ingredients)
        }
    }
}
