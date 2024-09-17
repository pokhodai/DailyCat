package com.cat.school.local.presentation.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.cat.school.local.R
import com.cat.school.local.model.TabItemEntry
import com.cat.school.local.nav.container.IContainerNavProvider
import com.cat.school.local.nav.container.LocalNavContainerHolder
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
        AppNavigator(requireActivity(), R.id.tab_container_id, childFragmentManager)
    }

    @Inject
    lateinit var localNavContainerHolder: LocalNavContainerHolder

    private var isPressedBackFirstTime: Boolean = false

    private val tabItemEntry: TabItemEntry
        get() {
            val tabItemEntryName =
                arguments?.getString(EXTRA_TAB_ITEM_ENTRY_NAME) ?: TabItemEntry.TODAY.name
            return TabItemEntry.valueOf(tabItemEntryName)
        }

    private val toastExitApp by lazy {
        Toast.makeText(
            requireContext(),
            "Нажмите второй раз для выхода из приложения",
            Toast.LENGTH_SHORT
        )
    }

    override fun getCicerone(): Cicerone<Router> {
        return localNavContainerHolder.getCicerone(tabItemEntry)
    }

    override fun getRouter(): Router {
        return getCicerone().router
    }

    private val onBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!isVisible) {
                    return
                }

                val fragment = childFragmentManager.findFragmentById(R.id.tab_container_id)
                val isNotNullFragment = fragment != null
                if (!isNotNullFragment) {
                    return
                }
                val backStackEntryCount = childFragmentManager.backStackEntryCount
                when {
                    backStackEntryCount >= 1 -> {
                        getRouter().exit()
                    }

                    backStackEntryCount == 0 -> {
                        if (!isPressedBackFirstTime) {
                            isPressedBackFirstTime = true
                            toastExitApp.show()
                        } else {
                            getRouter().exit()
                        }
                    }
                }
            }
        }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        isPressedBackFirstTime = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val containerFragment = childFragmentManager.findFragmentById(R.id.tab_container_id)
        if (containerFragment == null) {
            val fragment = BottomNavScreens.getBottomTabFragment(tabItemEntry)
            getCicerone().router.replaceScreen(fragment)
        }
        addOnBackPressedDispatcher()
        val tabContainer = FrameLayout(requireContext())
        tabContainer.layoutParams = ViewGroup.LayoutParams(
            MATCH_PARENT,
            MATCH_PARENT
        )
        tabContainer.id = R.id.tab_container_id
        return tabContainer
    }

    private fun addOnBackPressedDispatcher() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }

    override fun onResume() {
        super.onResume()
        isPressedBackFirstTime = false
        getCicerone().getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        isPressedBackFirstTime = false
        getCicerone().getNavigatorHolder().removeNavigator()
        super.onPause()
    }

    override fun onDestroyView() {
        onBackPressedCallback.remove()
        super.onDestroyView()
    }

    companion object {
        private const val EXTRA_TAB_ITEM_ENTRY_NAME = "tcf_extra_tab_item_entry_name"

        fun getNewInstance(tabItemEntry: TabItemEntry) =
            TabContainerFragment().apply {
                arguments = bundleOf(EXTRA_TAB_ITEM_ENTRY_NAME to tabItemEntry.name)
            }
    }
}