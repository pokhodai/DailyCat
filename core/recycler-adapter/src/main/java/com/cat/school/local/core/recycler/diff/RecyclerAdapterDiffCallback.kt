package com.cat.school.local.core.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import com.cat.school.local.core.recycler.RecyclerState

internal class RecyclerAdapterDiffCallback : DiffUtil.ItemCallback<RecyclerState>() {
    override fun areItemsTheSame(
        oldItem: RecyclerState,
        newItem: RecyclerState
    ) = oldItem.provideId == newItem.provideId

    override fun areContentsTheSame(
        oldItem: RecyclerState,
        newItem: RecyclerState
    ) = oldItem.areContentsTheSame(newItem)
}