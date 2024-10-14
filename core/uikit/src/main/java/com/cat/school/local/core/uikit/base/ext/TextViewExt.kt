package com.cat.school.local.core.uikit.base.ext

import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import com.cat.school.local.core.uikit.base.value.ColorValue
import com.cat.school.local.core.uikit.base.value.FontValue
import com.cat.school.local.core.uikit.base.value.TextStyleValue
import com.cat.school.local.core.uikit.base.value.TextValue

fun TextView.bindTextOptional(value: String?) {
    isVisible = if (value != null) {
        text = value
        true
    } else {
        false
    }
}

fun TextView.bindTextOptional(value: TextValue?) {
    isVisible = if (value != null) {
        load(value)
        true
    } else {
        false
    }
}

fun TextView.load(value: TextValue) {
    text = value.value
    value.style?.let(::setTextStyle)
    gravity = value.gravity
}

fun TextView.setTextStyle(value: TextStyleValue) {
    when(value) {
        is TextStyleValue.Res -> setAppearance(value.value)
        is TextStyleValue.Custom -> {
            setTextColorValue(value.color)
            setFontStyle(value.fontStyle)
            setTextSizeSp(value.size)
        }
    }
}

fun TextView.setAppearance(@StyleRes textAppearance: Int) =
    TextViewCompat.setTextAppearance(this, textAppearance)

fun TextView.setFontStyle(fontStyle: FontValue) {
    typeface = getFont(fontStyle.fontResId)
}

fun TextView.setTextColorValue(value: ColorValue) {
    val colorInt = when(value) {
        is ColorValue.Color -> value.value
        is ColorValue.Res -> getColor(value.value)
    }
    setTextColor(colorInt)
}

fun TextView.setTextSizeSp(size: Float) {
    setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
}