package com.cat.school.local.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.cat.school.core.common.ext.hideKeyboard
import com.cat.school.core.common.ext.observe
import com.cat.school.local.R
import com.cat.school.local.common.ext.addItem
import com.cat.school.local.common.ext.showSnackBar
import com.cat.school.local.core.model.ScreenModel
import com.cat.school.local.model.TabItemEntry
import com.cat.school.local.databinding.ActivityMainBinding
import com.cat.school.local.nav.holders.RootNavRouterHolder
import com.cat.school.local.nav.providers.ContainerNavRouterProvider
import com.cat.school.local.nav.providers.RootNavRouterProvider
import com.cat.school.local.screens.BottomNavScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppActivity : FragmentActivity(), RootNavRouterProvider {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private val viewModel by viewModels<AppActivityViewModel>()

    private var onBackStackChangedListener: FragmentManager.OnBackStackChangedListener? = null

    @Inject
    lateinit var rootNavRouterHolder: RootNavRouterHolder

    private var isHandleOnBackOnce: Boolean = false

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val container = getVisibleFragmentContainer()
            val backStackEntry = container?.childFragmentManager?.backStackEntryCount ?: 0
            when {
                backStackEntry >= 1 -> {
                    rootNavRouterHolder.pop()
                }
                !isHandleOnBackOnce -> {
                    isHandleOnBackOnce = true
                    if (container is ContainerNavRouterProvider) {
                        container.onShowSnackBar("Нажмите еще раз для выхода")
                    }
                }
                isHandleOnBackOnce -> {
                    rootNavRouterHolder.pop()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        setBottomNavigationMenu()
        setObservable()
        if (savedInstanceState == null) {
            binding.appActivityBottomNavigation.selectedItemId = TabItemEntry.TODAY.idRes
        }
    }

    private fun setObservable() = with(viewModel) {
        changeVisibilityBottomNavFlow.observe(this@AppActivity) { isVisible ->
            binding.appActivityBottomNavigation.isVisible = isVisible
        }
    }

    override fun onResume() {
        super.onResume()
        onResetHandleBackPressedOnce()
        rootNavRouterHolder.setProvider(this)
        onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    override fun onPause() {
        rootNavRouterHolder.removeProvider()
        onBackPressedCallback.remove()
        super.onPause()
    }

    private fun setBottomNavigationMenu() {
        TabItemEntry.entries.forEach {
            binding.appActivityBottomNavigation.menu.addItem(
                idRes = it.idRes,
                nameRes = it.nameRes,
                iconRes = it.iconRes
            )
        }
        binding.appActivityBottomNavigation.setOnItemSelectedListener(::onItemSelected)
    }

    private fun getVisibleFragmentContainer(): Fragment? {
        val fm = supportFragmentManager
        val fragments = fm.fragments
        return fragments.find { it.isVisible && it is ContainerNavRouterProvider }
    }

    override fun onResetHandleBackPressedOnce() {
        isHandleOnBackOnce = false
    }

    override fun onShowSnackBar(message: String) {
        showSnackBar(
            view = window.decorView,
            message = message
        )
    }

    override fun onChangeScreen() {
        onBackStackChangedListener = null
        val container = getVisibleFragmentContainer()
        val onBackStackChangedListener = FragmentManager.OnBackStackChangedListener {
            if (container is ContainerNavRouterProvider) {
                viewModel.onChangeVisibilityBottomNavigation(container.getScreen())
            }
            onBackStackChangedListener?.let { listener ->
                container?.childFragmentManager?.removeOnBackStackChangedListener(listener)
            }
            onBackStackChangedListener = null
        }
        this.onBackStackChangedListener = onBackStackChangedListener
        container?.childFragmentManager?.addOnBackStackChangedListener(onBackStackChangedListener)
    }

    override fun getCicerone(): Cicerone<Router>? {
        val fragment = getVisibleFragmentContainer()
        return if (fragment is ContainerNavRouterProvider) {
            fragment.getCicerone()
        } else {
            null
        }
    }

    override fun getRouter(): Router? {
        val fragment = getVisibleFragmentContainer()
        return if (fragment is ContainerNavRouterProvider) {
            fragment.getRouter()
        } else {
            null
        }
    }

    override fun getScreen(): ScreenModel? {
        val fragment = getVisibleFragmentContainer()
        return if (fragment is ContainerNavRouterProvider) {
            fragment.getScreen()
        } else {
            null
        }
    }

    private fun onItemSelected(
        menuItem: MenuItem
    ): Boolean {
        val tabItemEntry = TabItemEntry.entries.find { it.idRes == menuItem.itemId }
        onResetHandleBackPressedOnce()
        return onTabSelected(tabItemEntry)
    }

    private fun onTabSelected(
        tabItemEntry: TabItemEntry?
    ): Boolean {
        if (tabItemEntry == null) {
            return false
        }

        val currentFragment = getVisibleFragmentContainer()
        val newFragment = supportFragmentManager.findFragmentByTag(tabItemEntry.name)

        if (currentFragment != null && newFragment != null && currentFragment === newFragment) {
            return false
        }

        val transaction = supportFragmentManager.beginTransaction()
        if (newFragment == null) {
            transaction.add(
                R.id.app_activity_root_container,
                BottomNavScreens
                    .getTabContainerFragment(tabItemEntry)
                    .createFragment(supportFragmentManager.fragmentFactory),
                tabItemEntry.name
            )
        }

        if (currentFragment != null) {
            currentFragment.hideKeyboard()
            transaction.hide(currentFragment)
        }

        if (newFragment != null) {
            transaction.show(newFragment)
        }

        transaction.commitNow()
        return true
    }
}