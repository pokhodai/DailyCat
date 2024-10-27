package com.cat.daily.local.core.uikit.base.ext

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.core.view.isVisible
import com.cat.daily.local.core.uikit.base.value.ColorValue
import com.cat.daily.local.core.uikit.base.value.ImageValue

fun ImageView.bindImageOptional(value: ImageValue?) {
    isVisible = value?.let {
        load(value)
        true
    } ?: false
}

fun ImageView.setTint(colorValue: ColorValue?) {
    imageTintList = colorValue?.let {
        ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_enabled)),
            intArrayOf(getColor(colorValue))
        )
    }
}

fun ImageView.setTint(@ColorInt colorInt: Int) {
    imageTintList = ColorStateList(
        arrayOf(intArrayOf(android.R.attr.state_enabled)),
        intArrayOf(colorInt)
    )
}

fun ImageView.load(value: ImageValue) {
    setImageResource(value.value)
    setTint(value.tint)
    scaleType = value.scaleType
    makeRounded(value.roundValue)
}