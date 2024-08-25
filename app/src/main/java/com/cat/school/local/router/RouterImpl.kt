package com.cat.school.local.router

import androidx.navigation.NavController
import com.cat.school.core.router.Router
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RouterImpl @Inject constructor(): Router {

    private var _navController: WeakReference<NavController>? = null
    private val navController: NavController
        get() = _navController?.get()!!

    override fun setNavController(navController: NavController) {
        _navController = WeakReference(navController)
    }
}