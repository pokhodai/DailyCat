package com.cat.school.local.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.cat.school.local.R
import com.cat.school.local.databinding.ActivityMainBinding
import com.cat.school.local.common.ext.addItem
import com.cat.school.local.model.TabItemEntry
import com.cat.school.local.nav.activity.LocalNavActivityHolder
import com.cat.school.local.nav.activity.IActivityNavProvider
import com.cat.school.local.nav.container.IContainerNavProvider
import com.cat.school.local.screens.BottomNavScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppActivity : FragmentActivity(), IActivityNavProvider {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private val viewModel by viewModels<AppActivityViewModel>()

    @Inject
    lateinit var localNavActivityHolder: LocalNavActivityHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavigationMenu()
        if (savedInstanceState == null) {
            binding.appActivityBottomNavigation.selectedItemId = TabItemEntry.TODAY.idRes
        }
    }

    override fun onResume() {
        super.onResume()
        localNavActivityHolder.setProvider(this)
    }

    override fun onPause() {
        localNavActivityHolder.removeProvider()
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

    private fun getVisibleContainer(): Fragment? {
        val fm = supportFragmentManager
        val fragments = fm.fragments
        return fragments.find { it.isVisible && it is IContainerNavProvider }
    }

    override fun getCicerone(): Cicerone<Router>? {
        val fragment = getVisibleContainer()
        return if (fragment is IContainerNavProvider) {
            fragment.getCicerone()
        } else {
            null
        }
    }

    override fun getRouter(): Router? {
        val fragment = getVisibleContainer()
        return if (fragment is IContainerNavProvider) {
            fragment.getRouter()
        } else {
            null
        }
    }

    private fun onItemSelected(
        menuItem: MenuItem
    ): Boolean {
        val tabItemEntry = TabItemEntry.entries.find { it.idRes == menuItem.itemId }
        return onTabSelected(tabItemEntry)
    }

    private fun onTabSelected(
        tabItemEntry: TabItemEntry?
    ): Boolean {
        if (tabItemEntry == null) {
            return false
        }

        val fm = supportFragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                currentFragment = f
                break
            }
        }
        val newFragment = fm.findFragmentByTag(tabItemEntry.name)
        if (currentFragment != null && newFragment != null && currentFragment === newFragment) {
            return false
        }

        val transaction = fm.beginTransaction()
        if (newFragment == null) {
            transaction.add(
                R.id.app_activity_root_container,
                BottomNavScreens
                    .getTabContainerFragment(tabItemEntry)
                    .createFragment(fm.fragmentFactory),
                tabItemEntry.name
            )
        }
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }
        if (newFragment != null) {
            transaction.show(newFragment)
        }
        transaction.commitNow()

        return true
    }
}