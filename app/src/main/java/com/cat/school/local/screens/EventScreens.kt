package com.cat.school.local.screens

import com.cat.school.local.feature.event.create.CreateEventFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object EventScreens {

    fun getCreateEventFragment() = FragmentScreen {
        CreateEventFragment()
    }
}