package com.cat.school.local.core.uikit.ext

import android.content.res.Resources

internal val Float.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

internal val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

internal val Int.px: Float
    get() = (this / Resources.getSystem().displayMetrics.density)