package com.example.recipebook.domain.repository

import com.example.recipebook.domain.model.RecipeDetailsModel

interface RecipeDetailsRepository {
    suspend fun getRecipeDetails(id: Int): RecipeDetailsModel
}
