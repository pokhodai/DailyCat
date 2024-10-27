package com.cat.daily.local.feature.assignments.creater.mappers

import com.cat.daily.local.core.assist.managers.ResManager
import com.cat.daily.local.core.uikit.base.value.ColorValue
import com.cat.daily.local.feature.assignments.R
import com.cat.daily.local.core.uikit.toolbar.ToolbarItem
import javax.inject.Inject
import com.cat.daily.local.core.uikit.R as uikitR

class AssignmentsCreaterUIMapper @Inject constructor(
    private val resManager: ResManager
) {

    fun mapToolbar(
        onClickLeading: (() -> Unit),
    ): ToolbarItem.State {
        return ToolbarItem.State(
            id = "assignments_creater_toolbar_id",
            backgroundColor = ColorValue.Color(resManager.getColor(uikitR.color.backgroundColor2)),
            title = resManager.getString(R.string.assignments_creater_toolbar),
            onClickLeading = onClickLeading
        )
    }
}