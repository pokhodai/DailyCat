package com.cat.school.presentation.mappers

import com.cat.school.core.managers.ResManager
import com.cat.school.core.uikit.ui.toolbar.ToolbarItem
import com.cat.school.feature.assignments.R
import javax.inject.Inject
import com.cat.school.core.uikit.R as uikitR

class AssignmentsCreaterUIMapper @Inject constructor(
    private val resManager: ResManager
) {

    fun mapToolbar(
        onClickLeading: (() -> Unit),
    ): ToolbarItem.State {
        return ToolbarItem.State(
            id = "assignments_creater_toolbar_id",
            leadingIcon = uikitR.drawable.ic_chevron_left,
            backgroundColorInt = resManager.getColor(uikitR.color.backgroundColor2),
            title = resManager.getString(R.string.assignments_creater_toolbar),
            onClickLeading = onClickLeading
        )
    }
}