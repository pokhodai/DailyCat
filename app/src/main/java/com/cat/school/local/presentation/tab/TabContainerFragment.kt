package com.cat.school.local.presentation.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.cat.school.local.R
import com.cat.school.local.model.TabItemEntry
import com.cat.school.local.nav.LocalNavHolder
import com.cat.school.local.nav.provider.IContainerNavProvider
import com.cat.school.local.screens.BottomNavScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TabContainerFragment : Fragment(), IContainerNavProvider {

    private val navigator: Navigator by lazy {
        AppNavigator(requireActivity(), R.id.tab_container, childFragmentManager)
    }

    @Inject
    lateinit var localNavHolder: LocalNavHolder

    private val tabItemEntry: TabItemEntry
        get() {
            val tabItemEntryName =
                arguments?.getString(EXTRA_TAB_ITEM_ENTRY_NAME) ?: TabItemEntry.TODAY.name
            return TabItemEntry.valueOf(tabItemEntryName)
        }

    override fun getCicerone(): Cicerone<Router> {
        return localNavHolder.getCicerone(tabItemEntry)
    }

    override fun getRouter(): Router {
        return getCicerone().router
    }

    private val onBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val fragment = childFragmentManager.findFragmentById(R.id.tab_container)
                if (fragment !is IContainerNavProvider) {
                    localNavHolder.pop()
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (childFragmentManager.findFragmentById(R.id.tab_container) == null) {
            val fragment = BottomNavScreens.getBottomTabFragment(tabItemEntry)
            getCicerone().router.replaceScreen(fragment)
        }
        addOnBackPressedDispatcher()
        return inflater.inflate(R.layout.fragment_container_tab, container, false)
    }

    private fun addOnBackPressedDispatcher() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }

    override fun onResume() {
        super.onResume()
        getCicerone().getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        getCicerone().getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    companion object {
        private const val EXTRA_TAB_ITEM_ENTRY_NAME = "tcf_extra_tab_item_entry_name"

        fun getNewInstance(tabItemEntry: TabItemEntry) =
            TabContainerFragment().apply {
                arguments = bundleOf(EXTRA_TAB_ITEM_ENTRY_NAME to tabItemEntry.name)
            }
    }
}