package com.example.recipebook.domain.di

import com.example.recipebook.domain.di.qualifies.IoDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @IoDispatchers
    fun provideIoDispatcher(): CoroutineDispatcher{
        return Dispatchers.IO
    }
}
