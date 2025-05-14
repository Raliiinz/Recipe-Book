package com.example.recipebook.domain.repository

import com.example.recipebook.domain.model.RecipeResult

interface RecipesRepository {
    suspend fun searchRecipes(ingredients: String): RecipeResult
}
