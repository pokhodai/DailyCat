package com.cat.school.core.uikit.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cat.school.core.uikit.adapter.diff.GlobalAdapterDiffCallback
import com.cat.school.core.uikit.adapter.item.GlobalItem

class GlobalAdapter: ListAdapter<GlobalItem, RecyclerView.ViewHolder>(
    AsyncDifferConfig.Builder(
        GlobalAdapterDiffCallback()
    ).build()
) {

    private val asyncListDiffer = AsyncListDiffer(this, GlobalAdapterDiffCallback())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {

        }
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun getItem(position: Int): GlobalItem = asyncListDiffer.currentList[position]

    override fun submitList(list: MutableList<GlobalItem>?, commitCallback: Runnable?) {
        asyncListDiffer.submitList(list, commitCallback)
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            else -> 1
        }
    }

    private companion object {

    }
}