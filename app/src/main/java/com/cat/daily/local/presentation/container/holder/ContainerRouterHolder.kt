package com.cat.daily.local.presentation.container.holder

import com.cat.daily.local.presentation.model.TabItemEntry
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Cicerone.Companion.create
import com.github.terrakok.cicerone.Router
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContainerRouterHolder @Inject constructor() {
    private val containers = ConcurrentHashMap<TabItemEntry, Cicerone<Router>>()

    fun getCicerone(tabItemEntry: TabItemEntry): Cicerone<Router> {
        val cicerone = containers.getOrPut(tabItemEntry) { create() }
        return cicerone
    }
}