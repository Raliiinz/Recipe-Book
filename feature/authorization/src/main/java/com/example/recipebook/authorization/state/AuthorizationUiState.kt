package com.example.recipebook.authorization.state

sealed class AuthorizationUiState {
    object Idle : AuthorizationUiState()
    object Loading : AuthorizationUiState()
}