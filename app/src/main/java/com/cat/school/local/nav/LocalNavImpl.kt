package com.cat.school.local.nav

import com.cat.school.local.core.nav.api.LocalNav
import com.cat.school.local.screens.EventScreens
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class LocalNavImpl @Inject constructor(
    private val holder: LocalNavHolder,
) : LocalNav {

    private val router: Router?
        get() = holder.getRouter()

    override fun goToEvent() {
        navigate(EventScreens.getCreateEventFragment())
    }

    override fun pop() {
        holder.pop()
    }

    private fun navigate(screen: FragmentScreen) {
        router?.navigateTo(screen)
    }
}