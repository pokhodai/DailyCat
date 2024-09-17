package com.cat.school.local.feature.event.create.mapper

import com.cat.school.core.common.managers.ResManager
import javax.inject.Inject
import com.cat.school.local.core.uikit.ui.toolbar.ToolbarItem
import com.cat.school.local.feature.event.R
import com.cat.school.local.core.uikit.R as uikitR

class CreateEventMapper @Inject constructor(
    private val resManager: ResManager,
) {

    fun mapCreateEventToolbarItemState(
        onClickBackPressed: () -> Unit,
    ): ToolbarItem.State {
        return ToolbarItem.State(
            id = "create_event_toolbar_id",
            leadingIcon = uikitR.drawable.ic_chevron_left,
            title = resManager.getString(R.string.event_toolbar_title),
            backgroundColorInt = resManager.getColor(uikitR.color.backgroundColor2),
            onClickLeading = onClickBackPressed
        )
    }
}