package com.cat.school.local.nav.holders

import com.cat.school.local.nav.providers.RootNavRouterProvider
import com.cat.school.local.nav.providers.ContainerNavRouterProvider
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class RootNavRouterHolder @Inject constructor() {
    private var provider: RootNavRouterProvider? = null

    fun setProvider(provider: RootNavRouterProvider) {
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
        provider?.onChangeScreen()
        getRouter()?.exit()
    }

    fun navigate(
        screen: FragmentScreen
    ) {
        onResetHandleBackPressedOnce()
        provider?.onChangeScreen()
        getRouter()?.navigateTo(screen)
    }

    private fun getContainerNav(): ContainerNavRouterProvider? {
        val provider = this.provider
        return if (provider is ContainerNavRouterProvider) {
            provider
        } else {
            null
        }
    }
}