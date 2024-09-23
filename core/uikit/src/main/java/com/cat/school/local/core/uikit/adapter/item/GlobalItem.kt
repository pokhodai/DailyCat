package com.cat.school.local.core.uikit.adapter.item

import com.cat.school.local.core.uikit.ui.calendar.CalendarItem
import com.cat.school.local.core.uikit.ui.edit.TextFieldItem
import com.cat.school.local.core.uikit.ui.task.TaskItem

sealed class GlobalItem(val provideId: String) {
    data class CalendarItemWrap(
        val state: CalendarItem.State
    ): GlobalItem(state.id)

    data class TaskItemWrap(
        val state: TaskItem.State
    ): GlobalItem(state.id)

    data class InputWrap(
        val state: TextFieldItem.State
    ): GlobalItem(state.id)
}