package com.cat.daily.local.core.uikit.base.ext

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Outline
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.cat.daily.local.core.uikit.R
import com.cat.daily.local.core.uikit.base.value.ColorValue
import com.cat.daily.local.core.uikit.base.value.DimensionValue
import com.cat.daily.local.core.uikit.base.value.RoundValue
import com.cat.daily.local.core.uikit.base.value.SizeValue

fun View.getDrawable(@DrawableRes id: Int) =
    ResourcesCompat.getDrawable(context.resources, id, context.theme)
fun View.getColor(@ColorRes id: Int) = ContextCompat.getColor(context, id)
fun View.getColorStateList(@ColorRes id: Int) = ContextCompat.getColorStateList(context, id)
fun View.getString(@StringRes id: Int) = ContextCompat.getString(context, id)
fun View.getFont(@FontRes font: Int) = ResourcesCompat.getFont(context, font)
fun View.getColor(value: ColorValue?): Int {
    val colorInt = when (value) {
        is ColorValue.Color -> value.value
        is ColorValue.Res -> getColor(value.value)
        else -> Color.TRANSPARENT
    }
    return colorInt
}

fun View.setBackgroundView(value: ColorValue?) {
    setBackgroundColor(getColor(value))
}

fun View.applyPadding(
    dpRect: DimensionValue.Rect?
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
    dpRect: DimensionValue.Rect?
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

fun View.makeRounded(value: DimensionValue.Dp) {
    this.outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            outline.setRoundRect(
                0,
                0,
                view.width,
                view.height,
                value.value.toFloat()
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

fun View.hideKeyboard() {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.makeRounded(value: RoundValue.Corners) {
    clipToOutline = true
    outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            when {
                value.isAllCorners() -> {
                    outline.setRoundRect(
                        view.left,
                        view.top,
                        view.right,
                        view.bottom,
                        value.rightTopRound.value.toFloat()
                    )
                }

                value.isOnlyTopCorners() -> {
                    outline.setRoundRect(
                        view.left,
                        view.top,
                        view.right,
                        view.bottom + value.rightTopRound.value,
                        value.rightTopRound.value.toFloat()
                    )
                }

                value.isOnlyBottomCorners() -> {
                    outline.setRoundRect(
                        view.left,
                        view.top - value.rightBottomRound.value,
                        view.right,
                        view.bottom,
                        value.rightBottomRound.value.toFloat()
                    )
                }

                value.isOnlyLeftCorners() -> {
                    outline.setRoundRect(
                        view.left,
                        view.top,
                        view.right + value.leftTopRound.value,
                        view.bottom,
                        value.leftTopRound.value.toFloat()
                    )
                }

                value.isOnlyRightCorners() -> {
                    outline.setRoundRect(
                        view.left - value.rightBottomRound.value,
                        view.top,
                        view.right,
                        view.bottom,
                        value.rightBottomRound.value.toFloat()
                    )
                }

                value.rightTopRound.value > 0 -> {
                    outline.setRoundRect(
                        view.left - value.rightTopRound.value,
                        view.top,
                        view.right,
                        view.bottom + value.rightTopRound.value,
                        value.rightTopRound.value.toFloat()
                    )
                }

                value.leftTopRound.value > 0 -> {
                    outline.setRoundRect(
                        view.left,
                        view.top,
                        view.right + value.leftTopRound.value,
                        view.bottom + value.leftTopRound.value,
                        value.leftTopRound.value.toFloat()
                    )
                }

                value.leftBottomRound.value > 0 -> {
                    outline.setRoundRect(
                        view.left,
                        view.top - value.leftBottomRound.value,
                        view.right + value.leftBottomRound.value,
                        view.bottom,
                        value.leftBottomRound.value.toFloat()
                    )
                }

                value.rightBottomRound.value > 0 -> {
                    outline.setRoundRect(
                        view.left - value.rightBottomRound.value,
                        view.top - value.rightBottomRound.value,
                        view.right,
                        view.bottom,
                        value.rightBottomRound.value.toFloat()
                    )
                }
            }
        }
    }
}

fun View.makeRounded(value: RoundValue?) {
    when (value) {
        is RoundValue.Corners -> makeRounded(value)
        is RoundValue.Radius -> makeRounded(value.value)
        else -> makeRounded(DimensionValue.Dp(0))
    }
}

private fun RoundValue.Corners.isOnlyRightCorners() = leftTopRound.value == 0 &&
        leftBottomRound.value == 0 &&
        rightBottomRound.value > 0 &&
        rightBottomRound == rightTopRound

private fun RoundValue.Corners.isOnlyLeftCorners() = rightTopRound.value == 0 &&
        rightBottomRound.value == 0 &&
        leftTopRound.value > 0 &&
        leftTopRound == leftBottomRound

private fun RoundValue.Corners.isOnlyBottomCorners() = leftTopRound.value == 0 &&
        rightTopRound.value == 0 &&
        rightBottomRound.value > 0 &&
        rightBottomRound == leftBottomRound

private fun RoundValue.Corners.isOnlyTopCorners() = leftBottomRound.value == 0 &&
        rightBottomRound.value == 0 &&
        rightTopRound.value > 0 &&
        rightTopRound == leftTopRound

private fun RoundValue.Corners.isAllCorners() = rightTopRound.value > 0 &&
        rightTopRound == rightBottomRound &&
        rightTopRound == leftTopRound &&
        rightTopRound == leftBottomRound

fun View.setSizeValue(value: SizeValue) {
    val lp = layoutParams
    lp.width = value.width.value
    lp.height = value.height.value
    layoutParams = lp
}

fun View.setWithValue(value: DimensionValue) {
    setWidth(value.value)
}

fun View.setWidth(width: Int) {
    if (this.layoutParams != null && (this.width != width || this.layoutParams.width != width)) {
        val lp = layoutParams
        lp.width = width
        layoutParams = lp
    }
}

fun View.setHeightValue(value: DimensionValue) {
    setHeight(value.value)
}

fun View.setHeight(height: Int) {
    if (this.layoutParams != null && (this.height != height || this.layoutParams.height != height)) {
        val lp = layoutParams
        lp.height = height
        layoutParams = lp
    }
}