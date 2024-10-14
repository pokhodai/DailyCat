package com.cat.school.local.core.nav.provider

import com.cat.school.local.core.model.ScreenModel

interface FragmentNavRouterProvider {
    fun getScreen(): ScreenModel?
}