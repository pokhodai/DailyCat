package com.cat.daily.local.presentation.root.holder

import com.cat.daily.local.presentation.container.provider.IContainerRouterProvider
import com.cat.daily.local.presentation.root.provider.IRootRouterProvider
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class RootRouterHolder @Inject constructor() {
    private var provider: IRootRouterProvider? = null

    fun setProvider(provider: IRootRouterProvider) {
        this.provider = provider
    }

    fun removeProvider() {
        this.provider = null
    }

    private fun getRouter(): Router? {
        return getCicerone()?.router
    }

    private fun getCicerone(): Cicerone<Router>? {
        return getContainerRouter()?.getCicerone()
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

    private fun getContainerRouter(): IContainerRouterProvider? {
        val provider = this.provider
        return if (provider is IContainerRouterProvider) {
            provider
        } else {
            null
        }
    }
}