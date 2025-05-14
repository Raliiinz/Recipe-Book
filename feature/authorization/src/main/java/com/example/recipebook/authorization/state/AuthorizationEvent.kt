package com.example.recipebook.authorization.state

sealed class AuthorizationEvent {
    data class ShowError(val message: AuthorizationError) : AuthorizationEvent()
}

enum class AuthorizationError {
    InvalidCredentials,
    Unknown
}