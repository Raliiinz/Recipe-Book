package com.example.recipebook.navigation

interface NavMain {

    fun initNavMain(parent: Nav)

    fun goToSearchPage()

    fun goToDetailsPage(recipeId: Int)
}