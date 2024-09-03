package com.cat.school.local.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController.OnDestinationChangedListener
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.cat.school.local.R
import com.cat.school.local.databinding.ActivityMainBinding
import com.cat.school.local.navigator.INavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppActivity : FragmentActivity() {

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.app_activity_root_container) as NavHostFragment
    }

    private val navController by lazy {
        navHostFragment.navController
    }

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private val viewModel by viewModels<AppActivityViewModel>()

    private val destinationChangedListener =
        OnDestinationChangedListener { controller, destination, arguments ->
            viewModel.setNavController(controller)

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.appActivityBottomNavigation.setupWithNavController(navController)
        setListeners()
    }

    private fun setListeners() = with(binding) {
        navController.addOnDestinationChangedListener(destinationChangedListener)
        appActivityBottomFab.setOnClickListener {
            navController.navigate(R.id.actionAssignmentsCreaterFragment)
        }
    }
}