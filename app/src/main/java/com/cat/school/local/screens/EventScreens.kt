package com.cat.school.local.screens

import com.cat.school.local.feature.event.create.CreateEventFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object EventScreens {

    const val CREATE_EVENT_KEY = "CREATE_EVENT_KEY"

    fun getCreateEventFragment() = FragmentScreen(key = CREATE_EVENT_KEY) {
        CreateEventFragment()
    }
}