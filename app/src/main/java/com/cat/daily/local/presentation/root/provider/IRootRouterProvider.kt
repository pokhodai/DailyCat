package com.cat.daily.local.presentation.root.provider

import com.cat.daily.local.presentation.container.provider.IContainerRouterProvider

interface IRootRouterProvider : IContainerRouterProvider {
    fun onResetHandleBackPressedOnce()
    override fun onShowSnackBar(message: String)
    fun onChangeScreen()
}