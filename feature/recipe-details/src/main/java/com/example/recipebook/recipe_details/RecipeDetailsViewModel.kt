package com.example.recipebook.recipe_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebook.domain.exception.BadRequestException
import com.example.recipebook.domain.exception.ForbiddenException
import com.example.recipebook.domain.exception.NetworkException
import com.example.recipebook.domain.exception.NotFoundException
import com.example.recipebook.domain.exception.ServerException
import com.example.recipebook.domain.exception.UnauthorizedException
import com.example.recipebook.domain.usecase.GetRecipeDetailsUseCase
import com.example.recipebook.recipe_details.state.DetailsErrorEvent
import com.example.recipebook.recipe_details.state.DetailsScreenState
import com.example.recipebook.utils.Constants.LOADING_DELAY_MS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase
) : ViewModel() {

    private val _detailsState = MutableStateFlow<DetailsScreenState>(DetailsScreenState.Initial)
    val detailsState =_detailsState.asStateFlow()

    private val _errorEvent = MutableSharedFlow<DetailsErrorEvent>()
    val errorEvent = _errorEvent.asSharedFlow()

    fun getRecipeDetails(recipeId: Int) {
        viewModelScope.launch {
            _detailsState.update { DetailsScreenState.Loading }
            delay(LOADING_DELAY_MS)
            runCatching {
                getRecipeDetailsUseCase.invoke(recipeId)
            }.onSuccess { result ->
                _detailsState.update { DetailsScreenState.DetailsResult(result) }
            }.onFailure {
                handleError(it)
            }
        }
    }

    private suspend fun handleError(ex: Throwable) {
        val errorReason = when (ex) {
            is UnauthorizedException -> DetailsErrorEvent.FailureReason.Unauthorized
            is ForbiddenException -> DetailsErrorEvent.FailureReason.Forbidden
            is NotFoundException -> DetailsErrorEvent.FailureReason.NotFound
            is BadRequestException -> DetailsErrorEvent.FailureReason.BadRequest
            is ServerException -> DetailsErrorEvent.FailureReason.Server
            is NetworkException -> DetailsErrorEvent.FailureReason.Network
            else -> DetailsErrorEvent.FailureReason.Unknown
        }
        _errorEvent.emit(DetailsErrorEvent.Error(errorReason))
        _detailsState.update { DetailsScreenState.Initial }
    }
}
