package com.cat.school.local.nav

import com.cat.school.local.core.nav.router.EventNavRouter
import com.cat.school.local.core.nav.router.NavRouter
import com.cat.school.local.nav.holders.RootNavRouterHolder
import javax.inject.Inject

class NavRouterImpl @Inject constructor(
    private val rootNavRouterHolder: RootNavRouterHolder,
    private val eventNavRouter: EventNavRouter,
) : NavRouter,
    EventNavRouter by eventNavRouter {

    override fun pop() {
        rootNavRouterHolder.pop()
    }
}