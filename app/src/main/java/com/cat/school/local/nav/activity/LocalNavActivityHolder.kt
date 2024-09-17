package com.cat.school.local.nav.activity

import com.cat.school.local.nav.container.IContainerNavProvider
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class LocalNavActivityHolder @Inject constructor() {
    private var provider: IActivityNavProvider? = null

    fun setProvider(provider: IActivityNavProvider) {
        this.provider = provider
    }

    fun removeProvider() {
        this.provider = null
    }

    fun getRouter(): Router? {
        return getCicerone()?.router
    }

    fun getCicerone(): Cicerone<Router>? {
        return getContainerNav()?.getCicerone()
    }

    fun pop() {
        getRouter()?.exit()
    }

    private fun getContainerNav(): IContainerNavProvider? {
        val provider = this.provider
        return if (provider is IContainerNavProvider) {
            provider
        } else {
            null
        }
    }
}