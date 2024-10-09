package com.cat.school.local.nav.providers

interface RootNavProvider : ContainerNavProvider {
    fun onResetHandleBackPressedOnce()
    override fun onShowSnackBar(message: String)
    fun onChangeScreen()
}