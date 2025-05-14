package com.example.recipebook.nav

import android.os.Bundle
import com.example.recipebook.app.R
import com.example.recipebook.navigation.Nav
import com.example.recipebook.navigation.NavMain

import javax.inject.Inject

class NavMainImpl @Inject constructor(
    private val navigatorDelegate: NavigatorDelegate,
) : NavMain {

    private var parent: Nav? = null

    override fun initNavMain(parent: Nav) {
        this.parent = parent
    }

    override fun goToSearchPage() {
        navigatorDelegate.navigate(action = R.id.action_global_search)
    }

    override fun goToDetailsPage(recipeId: Int) {
        val args = Bundle().apply {
            putInt("recipeId", recipeId)
        }
        navigatorDelegate.navigate(
            action = R.id.action_global_details,
            args = args
        )
    }
}
