package com.cat.school.local.screens

import com.cat.school.local.core.model.ScreenModel
import com.cat.school.local.feature.event.create.CreateEventFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object EventScreens {

    fun getCreateEventFragment(
        key: String
    )= FragmentScreen(
        key = key
    ) {
        CreateEventFragment()
    }
}