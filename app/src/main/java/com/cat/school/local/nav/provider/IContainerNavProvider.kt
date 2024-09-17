package com.cat.school.local.nav.provider

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

interface IContainerNavProvider {
    fun getCicerone(): Cicerone<Router>?
    fun getRouter(): Router?
}