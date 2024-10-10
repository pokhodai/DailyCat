package com.cat.school.local.core.uikit.ext

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Outline
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.core.widget.TextViewCompat
import com.cat.school.local.core.uikit.R
import com.cat.school.local.core.uikit.base.IconState
import com.cat.school.local.core.uikit.base.ViewDimension


fun View.getDrawable(@DrawableRes id: Int) =
    ResourcesCompat.getDrawable(context.resources, id, context.theme)

fun View.getColor(@ColorRes id: Int) = ContextCompat.getColor(context, id)
fun View.getColorStateList(@ColorRes id: Int) = ContextCompat.getColorStateList(context, id)
fun View.getString(@StringRes id: Int) = ContextCompat.getString(context, id)
fun View.getFont(@FontRes font: Int) = ResourcesCompat.getFont(context, font)

fun TextView.setAppearance(@StyleRes textAppearance: Int) =
    TextViewCompat.setTextAppearance(this, textAppearance)

fun View.applyPadding(
    dpRect: ViewDimension.Rect?
) {
    setPadding(
        dpRect?.valueLeft ?: 0,
        dpRect?.valueTop ?: 0,
        dpRect?.valueRight ?: 0,
        dpRect?.valueBottom ?: 0
    )
}

fun View.applyPadding(
    left: Int = this.paddingLeft,
    top: Int = this.paddingTop,
    right: Int = this.paddingRight,
    bottom: Int = this.paddingBottom
) {
    setPadding(left, top, right, bottom)
}

fun View.applyMargin(
    dpRect: ViewDimension.Rect?
) {
    applyMargin(
        left = dpRect?.valueLeft ?: 0,
        top = dpRect?.valueTop ?: 0,
        bottom = dpRect?.valueBottom ?: 0,
        right = dpRect?.valueRight ?: 0
    )
}

fun View.applyMargin(
    left: Int,
    top: Int,
    right: Int,
    bottom: Int
) {
    val lp = this.layoutParams
    if (lp !is ViewGroup.MarginLayoutParams) return

    val isNeedChange = lp.leftMargin != left ||
            lp.topMargin != top ||
            lp.rightMargin != right ||
            lp.bottomMargin != bottom

    if (isNeedChange) {
        lp.leftMargin = left
        lp.topMargin = top
        lp.rightMargin = right
        lp.bottomMargin = bottom
        this.layoutParams = lp
    }
}

fun View.makeRounded(radius: Int) {
    this.outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            outline.setRoundRect(
                0,
                0,
                view.width,
                view.height,
                radius.toFloat()
            )
        }
    }

    this.clipToOutline = true
}

fun View.makeRippleDrawable(
    @ColorInt rippleColor: Int,
    shapeDrawable: Int = GradientDrawable.RECTANGLE,
    cornerRadius: Float = 0f,
) {
    val mask = GradientDrawable().apply {
        shape = shapeDrawable
        setCornerRadius(cornerRadius)
        setColor(getColor(R.color.backgroundColor0))
    }

    foreground = RippleDrawable(
        ColorStateList.valueOf(rippleColor),
        null,
        mask
    )
}

fun View.setNewHeight(height: Int) {
    if (this.layoutParams != null && (this.height != height || this.layoutParams.height != height)) {
        val lp = layoutParams
        lp.height = height
        layoutParams = lp
    }
}

fun View.setNewWidth(width: Int) {
    if (this.layoutParams != null && (this.width != width || this.layoutParams.width != width)) {
        val lp = layoutParams
        lp.width = width
        layoutParams = lp
    }
}

fun View.setNewWidthAndHeight(width: Int, height: Int) {
    val lp = layoutParams
    lp.width = width
    lp.height = height
    layoutParams = lp
}

fun TextView.bindTextOptional(value: String?) {
    isVisible = !value.isNullOrEmpty()
    text = value
}

fun ImageView.bindImageOptional(@DrawableRes imageRes: Int?) {
    isVisible = imageRes?.let {
        setImageResource(it)
        true
    } ?: false
}

fun ImageView.bindImageOptional(state: IconState?) {
    isVisible = state?.let {
        setImageResource(it.value)
        imageTintList = it.tint?.let(::getColorStateList)
        true
    } ?: false
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun ImageView.setTint(@ColorInt colorInt: Int) {
    ColorStateList(
        arrayOf(intArrayOf(android.R.attr.state_enabled)),
        intArrayOf(colorInt)
    )
}