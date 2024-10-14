package com.cat.daily.local.core.uikit.base.value

import android.view.Gravity
import androidx.annotation.GravityInt

data class TextValue(
    val value: CharSequence,
    val style: TextStyleValue? = null,
    @GravityInt val gravity: Int = Gravity.START,
)