package com.example.recipebook.nav

import com.example.recipebook.app.R
import com.example.recipebook.navigation.Nav
import com.example.recipebook.navigation.NavRegistration
import javax.inject.Inject

class NavRegistrationImpl @Inject constructor(
    private val navigatorDelegate: NavigatorDelegate,
) : NavRegistration {

    private var parent: Nav? = null

    override fun initNavMain(parent: Nav) {
        this.parent = parent
    }

    override fun goToAuthPage() {
        navigatorDelegate.navigate(action = R.id.action_global_auth)
    }

}
