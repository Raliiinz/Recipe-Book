package com.example.recipebook.data.repository

import retrofit2.HttpException
import com.example.recipebook.data.mapper.RecipeDetailsResponseMapper
import com.example.recipebook.domain.exception.NetworkException
import com.example.recipebook.domain.model.RecipeDetailsModel
import com.example.recipebook.domain.repository.RecipeDetailsRepository
import com.example.recipebook.domain.util.ErrorHandler
import com.example.recipebook.network.RecipeApi
import java.io.IOException
import javax.inject.Inject

class RecipeDetailsRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi,
    private val mapper: RecipeDetailsResponseMapper,
    private val errorHandler: ErrorHandler
): RecipeDetailsRepository {

    override suspend fun getRecipeDetails(id: Int): RecipeDetailsModel {
        return try {
            val response = recipeApi.getRecipeDetails(id)

            if (response.isSuccessful) {
                response.body()?.let { recipe ->
                    mapper.map(recipe)
                } ?: throw errorHandler.handleHttpException(response.code())
            } else {
                throw errorHandler.handleHttpException(response.code())
            }
        } catch (ioe: IOException) {
            throw NetworkException("Ошибка сети: ${ioe.message ?: "неизвестная ошибка"}").apply {
                initCause(ioe)
            }
        } catch (httpException: HttpException) {
            throw errorHandler.handleHttpException(httpException.code()).apply {
                initCause(httpException)
            }
        }
    }
}
