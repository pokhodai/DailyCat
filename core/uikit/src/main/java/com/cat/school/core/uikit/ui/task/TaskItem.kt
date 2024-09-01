package com.cat.school.core.uikit.ui.task

import androidx.annotation.ColorInt
import com.cat.school.core.uikit.ui.checkbox.CheckboxCompositeItem
import com.cat.school.core.uikit.utils.ViewDimension

class TaskItem {

    interface View {
        fun bindState(state: State)
    }

    data class State(
        val id: String,
        val name: String,
        val paddings: ViewDimension.Rect? = null,
        val startTime: String,
        val endTime: String,
        @ColorInt val taskColorInt: Int,
        val reminder: String? = null,
        val place: String? = null,
        var isExpanded: Boolean = false,
        val missingAssignmentCount: String? = null,
        val assignments: List<CheckboxCompositeItem.State>? = null,
    )
}