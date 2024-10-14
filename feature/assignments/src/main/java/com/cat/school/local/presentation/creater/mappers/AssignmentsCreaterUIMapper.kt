package com.cat.school.local.presentation.creater.mappers

import com.cat.school.core.common.managers.ResManager
import com.cat.school.feature.assignments.R
import com.cat.school.local.core.uikit.toolbar.ToolbarItem
import javax.inject.Inject
import com.cat.school.local.core.uikit.R as uikitR

class AssignmentsCreaterUIMapper @Inject constructor(
    private val resManager: ResManager
) {

    fun mapToolbar(
        onClickLeading: (() -> Unit),
    ): ToolbarItem.State {
        return ToolbarItem.State(
            id = "assignments_creater_toolbar_id",
            backgroundColorInt = resManager.getColor(uikitR.color.backgroundColor2),
            title = resManager.getString(R.string.assignments_creater_toolbar),
            onClickLeading = onClickLeading
        )
    }
}