package com.cat.daily.local.core.uikit.base.ext

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.core.view.isVisible
import com.cat.daily.local.core.uikit.base.value.ImageValue

fun ImageView.bindImageOptional(value: ImageValue?) {
    isVisible = value?.let {
        load(value)
        true
    } ?: false
}

fun ImageView.setTint(@ColorInt colorInt: Int) {
    ColorStateList(
        arrayOf(intArrayOf(android.R.attr.state_enabled)),
        intArrayOf(colorInt)
    )
}

fun ImageView.load(value: ImageValue) {
    setImageResource(value.value)
    imageTintList = value.tint?.let(::getColorStateList)
    scaleType = value.scaleType
    makeRounded(value.roundValue)
}