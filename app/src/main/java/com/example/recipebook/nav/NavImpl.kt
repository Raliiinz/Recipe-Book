package com.example.recipebook.nav

import com.example.recipebook.navigation.Nav
import com.example.recipebook.navigation.NavMain
import javax.inject.Inject

class NavImpl @Inject constructor(
    private val navigatorDelegate: NavigatorDelegate,
    private val navMain: NavMain,
) : Nav, NavMain by navMain {

    init {
        initNavMain(parent = this)
    }

    override fun setNavProvider(navProvider: Nav.Provider) {
        navigatorDelegate.setNavProvider(navProvider = navProvider)
    }

    override fun clearNavProvider(navProvider: Nav.Provider) {
        navigatorDelegate.clearNavProvider(navProvider = navProvider)
    }
}
