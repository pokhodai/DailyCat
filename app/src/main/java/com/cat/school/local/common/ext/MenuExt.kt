package com.cat.school.local.common.ext

import android.view.Menu
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes

fun Menu.addItem(
    @IdRes idRes: Int,
    @StringRes nameRes: Int,
    @DrawableRes iconRes: Int,
) {
    add(
        Menu.NONE,
        idRes,
        Menu.NONE,
        nameRes
    ).setIcon(iconRes)
}