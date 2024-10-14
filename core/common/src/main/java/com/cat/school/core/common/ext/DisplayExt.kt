package com.cat.school.core.common.ext

import android.content.res.Resources

val Float.dp: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Float
    get() = (this / Resources.getSystem().displayMetrics.density)