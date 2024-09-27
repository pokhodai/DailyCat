package com.cat.school.local.nav.activity

import com.cat.school.local.nav.container.IContainerNavProvider

interface IActivityNavProvider : IContainerNavProvider {
    fun onResetHandleBackPressedOnce()
    override fun onShowSnackBar(message: String)
}