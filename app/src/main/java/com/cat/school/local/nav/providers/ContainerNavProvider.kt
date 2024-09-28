package com.cat.school.local.nav.providers

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

interface ContainerNavProvider {
    fun getCicerone(): Cicerone<Router>?
    fun getRouter(): Router?
    fun onShowSnackBar(message: String)
}