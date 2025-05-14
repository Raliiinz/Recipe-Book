package com.example.recipebook

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.recipebook.app.R
import com.example.recipebook.authorization.AuthorizationViewModel
import com.example.recipebook.navigation.Nav
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Nav.Provider {

    @Inject
    lateinit var nav: Nav
    private val mainContainerId = R.id.main_fragment_container
    private var navController: NavController? = null
    private val viewModel: AuthorizationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
        viewModel.navigateBasedOnAuthState()
    }

    private fun setupNavigation() {
        if (navController == null) {
            val navHost = supportFragmentManager.findFragmentById(mainContainerId) as NavHostFragment
            navController = navHost.navController
        }
        nav.setNavProvider(navProvider = this)
    }

    override fun getNavController(): NavController? {
        return navController
    }

    override fun onDestroy() {
        super.onDestroy()
        if (this::nav.isInitialized) {
            nav.clearNavProvider(navProvider = this)
        }
    }
}
