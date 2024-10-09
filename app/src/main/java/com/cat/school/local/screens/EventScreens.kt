package com.cat.school.local.screens

import com.cat.school.local.feature.event.create.CreateEventFragment
import com.cat.school.local.core.model.ScreenKeyEntry
import com.github.terrakok.cicerone.androidx.FragmentScreen

object EventScreens {

    fun getCreateEventFragment(
        key: ScreenKeyEntry
    )= FragmentScreen(
        key = key.name
    ) {
        CreateEventFragment()
    }
}