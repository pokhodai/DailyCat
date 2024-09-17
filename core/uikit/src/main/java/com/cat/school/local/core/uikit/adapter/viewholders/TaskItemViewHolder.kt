package com.cat.school.local.core.uikit.adapter.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.cat.school.local.core.uikit.ui.task.TaskItem
import com.cat.school.local.core.uikit.ui.task.TaskItemView

class TaskItemViewHolder(
    private val view: TaskItemView
) : RecyclerView.ViewHolder(view) {

    fun bind(state: TaskItem.State) {
        view.bindState(state)
    }
}