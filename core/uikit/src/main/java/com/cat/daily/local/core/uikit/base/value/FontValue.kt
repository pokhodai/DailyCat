package com.cat.daily.local.core.uikit.base.value

import androidx.annotation.FontRes
import com.cat.daily.local.core.uikit.R

enum class FontValue(
    @FontRes val fontResId: Int
) {
    BOLD(R.font.bold),
    MEDIUM(R.font.medium),
    REGULAR(R.font.regular),
    EXTRABOLD(R.font.extrabold),
}