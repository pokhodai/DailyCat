package com.cat.daily.local.core.uikit.base.value

import androidx.annotation.StyleRes

sealed interface TextStyleValue {
    data class Res(@StyleRes val value: Int) : TextStyleValue
    data class Custom(
        val color: ColorValue,
        val size: Float,
        val fontStyle: FontValue
    ) : TextStyleValue
}
