package com.cat.school.local.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.cat.school.local.R
import com.cat.school.local.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppActivity : AppCompatActivity(R.layout.activity_main) {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private val viewModel by viewModels<AppActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onCreateNavController()
    }

    private fun onCreateNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.app_activity_root_container) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        viewModel.setNavController(navController)
        binding.appActivityBottomNavigation.setupWithNavController(navController)
    }
}