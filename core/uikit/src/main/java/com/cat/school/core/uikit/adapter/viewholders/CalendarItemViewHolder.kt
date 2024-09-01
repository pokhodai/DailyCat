package com.cat.school.core.uikit.adapter.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.cat.school.core.uikit.ui.calendar.CalendarItem
import com.cat.school.core.uikit.ui.calendar.CalendarItemView

class CalendarItemViewHolder(
    private val view: CalendarItemView
) : RecyclerView.ViewHolder(view) {

    fun bind(state: CalendarItem.State) {
        view.bindState(state)
    }
}