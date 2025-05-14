package com.example.recipebook.search.state

sealed interface SearchScreenEvent {
    data class OnSearchButtonClicked(val query: String) : SearchScreenEvent
    data class OnListItemClick(val recipeId: Int) : SearchScreenEvent
}
