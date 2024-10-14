package com.cat.school.local.core.model

sealed interface ScreenModel {

    data object CreateEvent: ScreenModel
    data object Today: ScreenModel
}