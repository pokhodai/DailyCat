package com.cat.school.local.nav.providers

import com.cat.school.local.core.model.ScreenModel
import com.cat.school.local.core.nav.provider.FragmentNavRouterProvider
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

interface ContainerNavRouterProvider: FragmentNavRouterProvider {
    fun getCicerone(): Cicerone<Router>?
    fun getRouter(): Router?
    fun onShowSnackBar(message: String)
    override fun getScreen(): ScreenModel?
}