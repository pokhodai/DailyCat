package com.cat.school.local.nav

import com.cat.school.local.model.TabItemEntry
import com.cat.school.local.nav.provider.IContainerNavProvider
import com.cat.school.local.nav.provider.IActivityNavProvider
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Cicerone.Companion.create
import com.github.terrakok.cicerone.Router
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalNavHolder @Inject constructor() {
    private val containers = ConcurrentHashMap<TabItemEntry, Cicerone<Router>>()
    private var provider: IActivityNavProvider? = null

    fun getCicerone(tabItemEntry: TabItemEntry): Cicerone<Router> {
        val cicerone = containers.getOrPut(tabItemEntry) { create() }
        return cicerone
    }

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