package com.example.recipebook.search.state

import com.example.recipebook.domain.model.RecipeResult


sealed class SearchUiEvent {
    data class ShowDataSourceToast(val source: RecipeResult.Source) : SearchUiEvent()
}
