package com.cat.school.local.nav.providers

interface RootNavRouterProvider : ContainerNavRouterProvider {
    fun onResetHandleBackPressedOnce()
    override fun onShowSnackBar(message: String)
    fun onChangeScreen()
}