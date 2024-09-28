package com.cat.school.local.nav.holders

import com.cat.school.local.nav.providers.RootNavProvider
import com.cat.school.local.nav.providers.ContainerNavProvider
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class RootNavHolder @Inject constructor() {
    private var provider: RootNavProvider? = null

    fun setProvider(provider: RootNavProvider) {
        this.provider = provider
    }

    fun removeProvider() {
        this.provider = null
    }

    private fun getRouter(): Router? {
        return getCicerone()?.router
    }

    private fun getCicerone(): Cicerone<Router>? {
        return getContainerNav()?.getCicerone()
    }

    private fun onResetHandleBackPressedOnce() {
        provider?.onResetHandleBackPressedOnce()
    }

    fun pop() {
        getRouter()?.exit()
    }

    fun navigate(
        screen: FragmentScreen
    ) {
        onResetHandleBackPressedOnce()
        getRouter()?.navigateTo(screen)
    }

    private fun getContainerNav(): ContainerNavProvider? {
        val provider = this.provider
        return if (provider is ContainerNavProvider) {
            provider
        } else {
            null
        }
    }
}