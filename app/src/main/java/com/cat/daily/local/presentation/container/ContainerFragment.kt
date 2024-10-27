package com.cat.daily.local.presentation.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.cat.daily.local.core.assist.ext.showSnackBar
import com.cat.daily.local.presentation.container.holder.ContainerRouterHolder
import com.cat.daily.local.presentation.container.provider.IContainerRouterProvider
import com.cat.daily.local.core.router.screen.Screen
import com.cat.daily.local.presentation.model.TabItemEntry
import com.cat.daily.local.core.router.provider.IScreenRouterProvider
import com.cat.daily.local.core.router.screen.ScreenKeys.TAB_ITEM_KEY
import com.cat.daily.local.feature.assignments.AssignmentsFragment
import com.cat.daily.local.feature.today.presentation.TodayFragment
import com.cat.daily.local.feature.schedule.presentation.ScheduleFragment
import com.cat.daily.local.feature.settings.presentation.SettingsFragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ContainerFragment : Fragment(), IContainerRouterProvider {

    private val tabItemEntry: TabItemEntry
        get() {
            val tabItemEntryName =
                arguments?.getString(TAB_ITEM_KEY) ?: TabItemEntry.TODAY.name
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
    lateinit var containerNavRouterHolder: ContainerRouterHolder

    override fun getCicerone(): Cicerone<Router> {
        return containerNavRouterHolder.getCicerone(tabItemEntry)
    }

    override fun getRouter(): Router {
        return getCicerone().router
    }

    override fun onShowSnackBar(message: String) {
        showSnackBar(message)
    }

    override fun getScreen(): Screen? {
        val last = childFragmentManager.fragments.lastOrNull()
        return if (last is IScreenRouterProvider) {
            last.getScreen()
        } else {
            null
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val containerFragment = childFragmentManager.findFragmentById(containerId)
        val tabContainer = FragmentContainerView(requireContext())
        tabContainer.layoutParams = ViewGroup.LayoutParams(
            MATCH_PARENT,
            MATCH_PARENT
        )
        tabContainer.id = containerId
        if (containerFragment == null) {
            val fragment = getBottomTabFragment(tabItemEntry)
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

    private companion object {
        fun getBottomTabFragment(tabItemEntry: TabItemEntry): FragmentScreen {
            return FragmentScreen(key = TAB_ITEM_KEY) {
                when(tabItemEntry) {
                    TabItemEntry.TODAY -> TodayFragment()
                    TabItemEntry.SCHEDULE ->  ScheduleFragment()
                    TabItemEntry.ASSIGNMENTS -> AssignmentsFragment()
                    TabItemEntry.SETTINGS -> SettingsFragment()
                }
            }
        }
    }
}