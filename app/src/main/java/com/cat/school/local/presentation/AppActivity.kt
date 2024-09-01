package com.cat.school.local.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.cat.school.local.R
import com.cat.school.local.databinding.ActivityMainBinding
import com.cat.school.presentation.today.TodayFragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.setNavController(navController)
        binding.appActivityBottomNavigation.setupWithNavController(navController)
    }
}