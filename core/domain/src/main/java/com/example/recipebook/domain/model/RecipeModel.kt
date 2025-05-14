package com.example.recipebook.domain.model

data class RecipeModel(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val usedIngredients: Int,
    val missedIngredients: Int,
)
