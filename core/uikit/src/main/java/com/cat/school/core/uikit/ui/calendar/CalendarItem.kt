package com.cat.school.core.uikit.ui.calendar

import androidx.annotation.ColorRes
import com.cat.school.core.uikit.R

class CalendarItem {

    interface View {
        fun bindState(state: State)
    }

    data class State(
        val id: String,
        val type: Type,
        val day: String,
        val month: String,
        val count: String? = null,
        val isSelected: Boolean = false,
    )

    enum class Type(@ColorRes val selectedColorRes: Int) {
        TODAY(R.color.actionColor0),
        SCHEDULE(R.color.backgroundColor2)
    }
}