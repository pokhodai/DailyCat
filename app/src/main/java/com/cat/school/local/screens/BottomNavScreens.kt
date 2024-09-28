package com.cat.school.local.screens

import com.cat.school.local.model.TabItemEntry
import com.cat.school.local.presentation.container.ContainerFragment
import com.cat.school.local.presentation.assignments.AssignmentsFragment
import com.cat.school.presentation.schedule.ScheduleFragment
import com.cat.school.presentation.settings.SettingsFragment
import com.cat.school.local.presentation.task.TodayFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object BottomNavScreens {

    fun getTabContainerFragment(tabItemEntry: TabItemEntry) = FragmentScreen {
        ContainerFragment.getTabContainer(tabItemEntry)
    }

    fun getBottomTabFragment(tabItemEntry: TabItemEntry): FragmentScreen {
        return FragmentScreen(clearContainer = true) {
            when(tabItemEntry) {
                TabItemEntry.TODAY -> TodayFragment()
                TabItemEntry.SCHEDULE ->  ScheduleFragment()
                TabItemEntry.ASSIGNMENTS -> AssignmentsFragment()
                TabItemEntry.SETTINGS -> SettingsFragment()
            }
        }
    }
}