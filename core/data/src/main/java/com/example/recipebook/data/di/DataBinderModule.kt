package com.example.recipebook.data.di

import com.example.recipebook.data.local.repository.UserPreferencesRepositoryImpl
import com.example.recipebook.data.local.repository.UserRepositoryImpl
import com.example.recipebook.data.repository.RecipeDetailsRepositoryImpl
import com.example.recipebook.data.repository.RecipesRepositoryImpl
import com.example.recipebook.domain.repository.RecipeDetailsRepository
import com.example.recipebook.domain.repository.RecipesRepository
import com.example.recipebook.domain.repository.UserPreferencesRepository
import com.example.recipebook.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataBinderModule {
    @Binds
    @Singleton
    fun bindRecipesRepositoryToImpl(impl: RecipesRepositoryImpl): RecipesRepository

    @Binds
    @Singleton
    fun bindRecipeDetailsRepositoryToImpl(impl: RecipeDetailsRepositoryImpl): RecipeDetailsRepository

    @Binds
    @Singleton
    fun bindUserPrefRepositoryToImpl(impl: UserPreferencesRepositoryImpl): UserPreferencesRepository

    @Binds
    @Singleton
    fun bindUserRepositoryToImpl(impl: UserRepositoryImpl): UserRepository
}
