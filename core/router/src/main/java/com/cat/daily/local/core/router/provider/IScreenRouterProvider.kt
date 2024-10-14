package com.cat.daily.local.core.router.provider

import com.cat.daily.local.core.router.screen.Screen

interface IScreenRouterProvider {
    fun getScreen(): Screen?
}