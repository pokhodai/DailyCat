package com.cat.daily.local.presentation.container.provider

import com.cat.daily.local.core.router.screen.Screen
import com.cat.daily.local.core.router.provider.IScreenRouterProvider
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

interface IContainerRouterProvider: IScreenRouterProvider {
    fun getCicerone(): Cicerone<Router>?
    fun getRouter(): Router?
    fun onShowSnackBar(message: String)
    override fun getScreen(): Screen?
}