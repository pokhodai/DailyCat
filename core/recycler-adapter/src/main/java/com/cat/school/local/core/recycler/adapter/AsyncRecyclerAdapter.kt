package com.cat.school.local.core.recycler.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cat.school.local.core.recycler.RecyclerState
import com.cat.school.local.core.recycler.base.BaseRecyclerAdapter
import com.cat.school.local.core.recycler.diff.RecyclerAdapterDiffCallback

class AsyncRecyclerAdapter : ListAdapter<RecyclerState, RecyclerView.ViewHolder>(
    AsyncDifferConfig.Builder(RecyclerAdapterDiffCallback()).build()
) {
    private val asyncListDiffer = AsyncListDiffer(this, RecyclerAdapterDiffCallback())

    private val baseAdapter = BaseRecyclerAdapter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return baseAdapter.onCreateViewHolder(
            parent = parent,
            viewType = viewType,
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        baseAdapter.onBindViewHolder(
            item = getItem(position),
            holder = holder,
        )
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun getItem(position: Int): RecyclerState = asyncListDiffer.currentList[position]

    override fun submitList(list: MutableList<RecyclerState>?, commitCallback: Runnable?) {
        asyncListDiffer.submitList(list, commitCallback)
    }

    override fun submitList(list: List<RecyclerState>?) {
        asyncListDiffer.submitList(list)
    }

    override fun getItemViewType(position: Int) =
        baseAdapter.getItemViewType(item = getItem(position))
}