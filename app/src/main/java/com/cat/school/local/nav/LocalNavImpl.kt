package com.cat.school.local.nav

import com.cat.school.local.core.nav.api.LocalNav
import com.cat.school.local.nav.activity.LocalNavActivityHolder
import com.cat.school.local.screens.EventScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class LocalNavImpl @Inject constructor(
    private val localNavActivityHolder: LocalNavActivityHolder,
) : LocalNav {

    private val router: Router?
        get() = localNavActivityHolder.getRouter()

    private val cicerone: Cicerone<Router>?
        get() = localNavActivityHolder.getCicerone()

    override fun goToEvent() {
        navigate(EventScreens.getCreateEventFragment())
    }

    override fun pop() {
        router?.exit()
    }

    private fun navigate(screen: FragmentScreen) {
        localNavActivityHolder.onResetHandleBackPressedOnce()
        router?.navigateTo(screen)
    }
}