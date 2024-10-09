package com.cat.school.local.nav

import com.cat.school.local.core.model.ScreenKeyEntry
import com.cat.school.local.core.nav.EventNav
import com.cat.school.local.nav.holders.RootNavHolder
import com.cat.school.local.screens.EventScreens
import javax.inject.Inject

class EventNavImpl @Inject constructor(
    private val rootNavHolder: RootNavHolder,
) : EventNav {

    override fun gotoCreateEvent() {
        rootNavHolder.navigate(EventScreens.getCreateEventFragment(key = ScreenKeyEntry.CREATE_EVENT))
    }
}