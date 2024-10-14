package com.cat.daily.local.presentation.root

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.cat.daily.local.core.assist.ext.addItem
import com.cat.daily.local.core.assist.ext.hideKeyboard
import com.cat.daily.local.core.assist.ext.observe
import com.cat.daily.local.R
import com.cat.daily.local.core.assist.ext.showSnackBar
import com.cat.daily.local.presentation.container.ContainerFragment
import com.cat.daily.local.presentation.container.provider.IContainerRouterProvider
import com.cat.daily.local.core.router.screen.Screen
import com.cat.daily.local.core.router.screen.ScreenKeys.TAB_ITEM_KEY
import com.cat.daily.local.databinding.ActivityMainBinding
import com.cat.daily.local.presentation.model.TabItemEntry
import com.cat.daily.local.presentation.root.holder.RootRouterHolder
import com.cat.daily.local.presentation.root.provider.IRootRouterProvider
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RootActivity : FragmentActivity(), IRootRouterProvider {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private val viewModel by viewModels<RootViewModel>()

    private var onBackStackChangedListener: FragmentManager.OnBackStackChangedListener? = null

    @Inject
    lateinit var rootRouterHolder: RootRouterHolder

    private var isHandleOnBackOnce: Boolean = false

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            val container = getVisibleFragmentContainer()
            val backStackEntry = container?.childFragmentManager?.backStackEntryCount ?: 0
            when {
                backStackEntry >= 1 -> {
                    rootRouterHolder.pop()
                }
                !isHandleOnBackOnce -> {
                    isHandleOnBackOnce = true
                    if (container is IContainerRouterProvider) {
                        container.onShowSnackBar("Нажмите еще раз для выхода")
                    }
                }
                isHandleOnBackOnce -> {
                    rootRouterHolder.pop()
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
        changeVisibilityBottomNavFlow.observe(this@RootActivity, ::onChangeVisibility)
    }

    private fun onChangeVisibility(isVisible: Boolean) {
        binding.appActivityBottomNavigation.isVisible = isVisible
    }

    override fun onResume() {
        super.onResume()
        onResetHandleBackPressedOnce()
        rootRouterHolder.setProvider(this)
        onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    override fun onPause() {
        rootRouterHolder.removeProvider()
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
        return fragments.find { it.isVisible && it is IContainerRouterProvider }
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
            if (container is IContainerRouterProvider) {
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
        return if (fragment is IContainerRouterProvider) {
            fragment.getCicerone()
        } else {
            null
        }
    }

    override fun getRouter(): Router? {
        val fragment = getVisibleFragmentContainer()
        return if (fragment is IContainerRouterProvider) {
            fragment.getRouter()
        } else {
            null
        }
    }

    override fun getScreen(): Screen? {
        val fragment = getVisibleFragmentContainer()
        return if (fragment is IContainerRouterProvider) {
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
                getContainerScreen(tabItemEntry).createFragment(supportFragmentManager.fragmentFactory),
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

    private companion object {
        fun getContainerScreen(tabItemEntry: TabItemEntry) = FragmentScreen {
            ContainerFragment().apply {
                arguments = bundleOf(TAB_ITEM_KEY to tabItemEntry.name)
            }
        }
    }
}