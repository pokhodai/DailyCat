package com.cat.school.local.core.uikit.base.value

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes

sealed interface ColorValue {
    data class Res(@ColorRes val value: Int) : ColorValue
    data class Color(@ColorInt val value: Int) : ColorValue
}