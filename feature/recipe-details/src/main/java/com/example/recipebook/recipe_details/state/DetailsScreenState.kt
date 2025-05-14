package com.example.recipebook.recipe_details.state

import com.example.recipebook.domain.model.RecipeDetailsModel

sealed interface DetailsScreenState {
    data object Initial : DetailsScreenState
    data object Loading : DetailsScreenState
    data class DetailsResult(val result: RecipeDetailsModel) : DetailsScreenState
}
