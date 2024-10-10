package com.cat.school.local.nav.providers

import com.cat.school.local.core.model.ScreenKeyEntry
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

interface ContainerNavRouterProvider {
    fun getCicerone(): Cicerone<Router>?
    fun getRouter(): Router?
    fun onShowSnackBar(message: String)
    fun getScreenName(): ScreenKeyEntry?
}