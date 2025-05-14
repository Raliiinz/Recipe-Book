package com.example.recipebook.recipe_details.state

sealed class DetailsErrorEvent {

    data class Error(val reason: FailureReason) : DetailsErrorEvent()

    enum class FailureReason {
        Unauthorized,
        Forbidden,
        NotFound,
        BadRequest,
        Server,
        Network,
        Unknown
    }
}
