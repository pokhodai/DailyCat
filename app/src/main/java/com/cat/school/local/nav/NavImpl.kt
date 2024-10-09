package com.cat.school.local.nav

import com.cat.school.local.core.model.ScreenKeyEntry
import com.cat.school.local.core.nav.EventNav
import com.cat.school.local.core.nav.Nav
import com.cat.school.local.nav.holders.RootNavHolder
import com.cat.school.local.screens.EventScreens
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