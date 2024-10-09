package com.cat.school.local.core.provider

import com.cat.school.local.core.model.ScreenKeyEntry

interface FragmentProvider {
    fun getScreenKey(): ScreenKeyEntry
}