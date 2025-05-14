package com.example.recipebook.data.repository

import com.example.recipebook.data.local.cache.RecipeCache
import com.example.recipebook.data.mapper.RecipeResponseMapper
import com.example.recipebook.domain.exception.NetworkException
import com.example.recipebook.domain.model.RecipeResult
import com.example.recipebook.domain.repository.RecipesRepository
import com.example.recipebook.domain.util.ErrorHandler
import com.example.recipebook.network.RecipeApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val recipeApi: RecipeApi,
    private val mapper: RecipeResponseMapper,
    private val errorHandler: ErrorHandler,
    private val recipeCache: RecipeCache
) : RecipesRepository {

    override suspend fun searchRecipes(ingredients: String): RecipeResult {

        recipeCache.get(ingredients)?.let { cachedData ->
           return RecipeResult(cachedData, RecipeResult.Source.CACHE)
        }

        return try {
            val response = recipeApi.searchRecipesByIngredients(ingredients)

            if (response.isSuccessful) {
                val result = response.body()?.mapNotNull { mapper.map(it) } ?: emptyList()

                recipeCache.updateCache(ingredients, result)

                RecipeResult(result, RecipeResult.Source.API)
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
