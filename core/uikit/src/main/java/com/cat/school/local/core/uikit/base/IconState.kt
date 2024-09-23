package com.cat.school.local.core.uikit.base

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class IconState(
    @DrawableRes val value: Int,
    @ColorRes val tint: Int? = null,
)