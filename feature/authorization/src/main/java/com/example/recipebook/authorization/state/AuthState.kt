package com.example.recipebook.authorization.state

data class AuthState(
    val isLoggedIn: Boolean = false,
    val userPhone: String? = null
)