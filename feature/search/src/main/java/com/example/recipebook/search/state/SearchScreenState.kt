package com.example.recipebook.search.state

import com.example.recipebook.domain.model.RecipeModel


sealed interface SearchScreenState {
    data object Initial : SearchScreenState
    data object Loading : SearchScreenState
    data class SearchResult(val result: List<RecipeModel>) : SearchScreenState
}
