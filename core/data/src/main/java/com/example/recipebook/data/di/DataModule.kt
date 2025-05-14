package com.example.recipebook.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.recipebook.data.local.cache.RecipeCache
import com.example.recipebook.data.local.database.AppDatabase
import com.example.recipebook.data.local.database.dao.UserDao
import com.example.recipebook.data.local.datasource.UserPreferencesDataSource

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    private val databaseName = "app_database"

    @Provides
    @Singleton
    fun provideRecipeCache(): RecipeCache = RecipeCache()

    @Provides
    @Singleton
    fun provideUserPreferencesDataSource(
        @ApplicationContext context: Context
    ): UserPreferencesDataSource {
        return UserPreferencesDataSource(context)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            databaseName
        ).build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }
}
