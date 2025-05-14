package com.example.recipebook.di

import com.example.recipebook.nav.NavAuthorizationImpl
import com.example.recipebook.nav.NavImpl
import com.example.recipebook.nav.NavMainImpl
import com.example.recipebook.nav.NavRegistrationImpl
import com.example.recipebook.navigation.Nav
import com.example.recipebook.navigation.NavAuthorization
import com.example.recipebook.navigation.NavMain
import com.example.recipebook.navigation.NavRegistration
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    @Singleton
    fun bindNavToImpl(impl: NavImpl): Nav

    @Binds
    @Singleton
    fun bindNavMainToImpl(impl: NavMainImpl): NavMain

    @Binds
    @Singleton
    fun bindNavAuthToImpl(impl: NavAuthorizationImpl): NavAuthorization

    @Binds
    @Singleton
    fun bindNavRegisterToImpl(impl: NavRegistrationImpl): NavRegistration
}
