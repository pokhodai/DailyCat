package com.cat.school.local.presentation.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.cat.school.local.model.TabItemEntry
import com.cat.school.local.nav.providers.ContainerNavProvider
import com.cat.school.local.nav.holders.ContainerNavHolder
import com.cat.school.local.screens.BottomNavScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ContainerFragment : Fragment(), ContainerNavProvider {

    private val tabItemEntry: TabItemEntry
        get() {
            val tabItemEntryName =
                arguments?.getString(EXTRA_TAB_ITEM_ENTRY_NAME) ?: TabItemEntry.TODAY.name
            return TabItemEntry.valueOf(tabItemEntryName)
        }

    private val containerId: Int
        get() = tabItemEntry.idRes

    private val navigator: Navigator by lazy {
        AppNavigator(
            requireActivity(),
            containerId,
            childFragmentManager
        )
    }

    @Inject
    lateinit var containerNavHolder: ContainerNavHolder

    override fun getCicerone(): Cicerone<Router> {
        return containerNavHolder.getCicerone(tabItemEntry)
    }

    override fun getRouter(): Router {
        return getCicerone().router
    }

    override fun onShowSnackBar(message: String) {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val containerFragment = childFragmentManager.findFragmentById(containerId)
        val tabContainer = FrameLayout(requireContext())
        tabContainer.layoutParams = ViewGroup.LayoutParams(
            MATCH_PARENT,
            MATCH_PARENT
        )
        tabContainer.id = containerId
        if (containerFragment == null) {
            val fragment = BottomNavScreens.getBottomTabFragment(tabItemEntry)
            getRouter().replaceScreen(fragment)
        }
        return tabContainer
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

        fun getTabContainer(tabItemEntry: TabItemEntry) =
            ContainerFragment().apply {
                arguments = bundleOf(EXTRA_TAB_ITEM_ENTRY_NAME to tabItemEntry.name)
            }
    }
}