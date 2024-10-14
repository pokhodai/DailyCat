package com.cat.school.local.core.uikit.base.value

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class ImageValue(
    @DrawableRes val value: Int,
    @ColorRes val tint: Int? = null,
    val roundValue: RoundValue? = null,
    val scaleType: ImageView.ScaleType = ImageView.ScaleType.FIT_CENTER,
)