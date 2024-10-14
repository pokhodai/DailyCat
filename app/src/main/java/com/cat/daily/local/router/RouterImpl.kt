package com.cat.daily.local.router

import com.cat.daily.local.core.router.EventRouter
import com.cat.daily.local.core.router.Router
import com.cat.daily.local.presentation.root.holder.RootRouterHolder
import javax.inject.Inject

class RouterImpl @Inject constructor(
    private val rootNavigatorHolder: RootRouterHolder,
    private val eventRouter: EventRouter,
) : Router,
    EventRouter by eventRouter {

    override fun pop() {
        rootNavigatorHolder.pop()
    }
}