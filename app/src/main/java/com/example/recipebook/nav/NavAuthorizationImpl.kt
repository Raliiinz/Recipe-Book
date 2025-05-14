package com.example.recipebook.nav

import android.os.Bundle
import com.example.recipebook.app.R
import com.example.recipebook.navigation.Nav
import com.example.recipebook.navigation.NavAuthorization
import javax.inject.Inject

class NavAuthorizationImpl @Inject constructor(
    private val navigatorDelegate: NavigatorDelegate,
) : NavAuthorization {

    private var parent: Nav? = null

    override fun initNavMain(parent: Nav) {
        this.parent = parent
    }

    override fun goToRegisterPage() {
        navigatorDelegate.navigate(action = R.id.action_global_register)
    }

    override fun goToAuthPage() {
        navigatorDelegate.navigate(action = R.id.action_global_auth)
    }

    override fun goToMainPage(userId: String) {
        val args = Bundle().apply {
            putString("userId", userId)
        }
        navigatorDelegate.navigate(
            action = R.id.action_global_search,
            args = args
        )
    }
}
