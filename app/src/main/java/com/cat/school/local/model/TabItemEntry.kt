package com.cat.school.local.model

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.cat.school.local.R

enum class TabItemEntry(
    @IdRes val idRes: Int,
    @StringRes val nameRes: Int,
    @DrawableRes val iconRes: Int,
) {
    TODAY(
        idRes = R.id.tab_today_id,
        nameRes = R.string.tab_first_menu_today,
        iconRes = R.drawable.ic_today,
    ),
    SCHEDULE(
        idRes = R.id.tab_schedule_id,
        nameRes = R.string.tab_second_menu_schedule,
        iconRes = R.drawable.ic_assignments
    ),
    ASSIGNMENTS(
        idRes = R.id.tab_assignments_id,
        nameRes = R.string.tab_third_menu_assignments,
        iconRes = R.drawable.ic_schedule
    ),
    SETTINGS(
        idRes = R.id.tab_settings_id,
        nameRes = R.string.tab_fourth_menu_settings,
        iconRes = R.drawable.ic_settings
    )
}