package com.cat.school.local.presentation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.cat.school.core.router.Router
import com.cat.school.local.router.RouterImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppActivityViewModel @Inject constructor(
    private val router: Router,
) : ViewModel() {

    fun setNavController(navController: NavController) {

    }
}