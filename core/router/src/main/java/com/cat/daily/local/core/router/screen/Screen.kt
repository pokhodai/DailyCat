package com.cat.daily.local.core.router.screen

sealed interface Screen {
    data object CreateEvent: Screen
    data object Today: Screen
}