package com.cat.daily.local.router

import com.cat.daily.local.core.router.EventRouter
import com.cat.daily.local.core.router.screen.ScreenKeys.CREATE_EVENT_KEY
import com.cat.daily.local.feature.event.create.CreateEventFragment
import com.cat.daily.local.presentation.root.holder.RootRouterHolder
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class EventRouterImpl @Inject constructor(
    private val rootNavigatorHolder: RootRouterHolder,
) : EventRouter {

    override fun actionCreateEvent() {
        rootNavigatorHolder.navigate(getCreateEventFragment())
    }

    private fun getCreateEventFragment() = FragmentScreen(
        key = CREATE_EVENT_KEY
    ) {
        CreateEventFragment()
    }
}