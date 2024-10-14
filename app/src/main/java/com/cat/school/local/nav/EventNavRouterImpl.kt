package com.cat.school.local.nav

import com.cat.school.local.core.nav.router.EventNavRouter
import com.cat.school.local.nav.holders.RootNavRouterHolder
import com.cat.school.local.screens.EventScreens
import javax.inject.Inject

class EventNavRouterImpl @Inject constructor(
    private val rootNavRouterHolder: RootNavRouterHolder,
) : EventNavRouter {

    override fun gotoCreateEvent() {
        rootNavRouterHolder.navigate(EventScreens.getCreateEventFragment("CreateEvent"))
    }
}