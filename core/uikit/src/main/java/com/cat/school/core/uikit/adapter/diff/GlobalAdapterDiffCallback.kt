package com.cat.school.core.uikit.adapter.diff

import androidx.recyclerview.widget.DiffUtil
import com.cat.school.core.uikit.adapter.item.GlobalItem

class GlobalAdapterDiffCallback : DiffUtil.ItemCallback<GlobalItem>() {
    override fun areItemsTheSame(
        oldItem: GlobalItem,
        newItem: GlobalItem
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: GlobalItem,
        newItem: GlobalItem
    ) = oldItem == newItem
}