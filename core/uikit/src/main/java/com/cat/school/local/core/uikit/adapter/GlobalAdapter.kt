package com.cat.school.local.core.uikit.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cat.school.local.core.uikit.adapter.diff.GlobalAdapterDiffCallback
import com.cat.school.local.core.uikit.adapter.item.GlobalItem
import com.cat.school.local.core.uikit.adapter.viewholders.CalendarItemViewHolder
import com.cat.school.local.core.uikit.adapter.viewholders.TaskItemViewHolder
import com.cat.school.local.core.uikit.adapter.viewholders.TextFieldViewHolder
import com.cat.school.local.core.uikit.ui.calendar.CalendarItemView
import com.cat.school.local.core.uikit.ui.edit.TextFieldItemView
import com.cat.school.local.core.uikit.ui.task.TaskItemView

class GlobalAdapter: ListAdapter<GlobalItem, RecyclerView.ViewHolder>(
    AsyncDifferConfig.Builder(
        GlobalAdapterDiffCallback()
    ).build()
) {
    private val asyncListDiffer = AsyncListDiffer(this,
        GlobalAdapterDiffCallback()
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CALENDAR_ITEM_VIEW_TYPE -> CalendarItemViewHolder(CalendarItemView(parent.context))
            TASK_ITEM_VIEW_TYPE -> TaskItemViewHolder(TaskItemView(parent.context))
            TEXT_FIELD_VIEW_TYPE -> TextFieldViewHolder(TextFieldItemView(parent.context))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is CalendarItemViewHolder -> holder.bind((item as GlobalItem.CalendarItemWrap).state)
            is TaskItemViewHolder -> holder.bind((item as GlobalItem.TaskItemWrap).state)
            is TextFieldViewHolder -> holder.bind((item as GlobalItem.InputWrap).state)
        }
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun getItem(position: Int): GlobalItem = asyncListDiffer.currentList[position]

    override fun submitList(list: MutableList<GlobalItem>?, commitCallback: Runnable?) {
        asyncListDiffer.submitList(list, commitCallback)
    }

    override fun submitList(list: List<GlobalItem>?) {
        asyncListDiffer.submitList(list)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is GlobalItem.CalendarItemWrap -> CALENDAR_ITEM_VIEW_TYPE
            is GlobalItem.TaskItemWrap -> TASK_ITEM_VIEW_TYPE
            is GlobalItem.InputWrap -> TEXT_FIELD_VIEW_TYPE
        }
    }

    private companion object {
        const val CALENDAR_ITEM_VIEW_TYPE = 1
        const val TASK_ITEM_VIEW_TYPE = 2
        const val TEXT_FIELD_VIEW_TYPE = 3
    }
}