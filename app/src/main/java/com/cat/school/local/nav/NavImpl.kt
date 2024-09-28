package com.cat.school.local.nav

import com.cat.school.local.core.nav.EventNav
import com.cat.school.local.core.nav.Nav
import com.cat.school.local.nav.holders.RootNavHolder
import javax.inject.Inject

class NavImpl @Inject constructor(
    private val rootNavHolder: RootNavHolder,
    private val eventNav: EventNav,
) : Nav,
    EventNav by eventNav {

    override fun pop() {
        rootNavHolder.pop()
    }
}