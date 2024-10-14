package com.cat.daily.local.core.recycler.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cat.daily.local.core.recycler.RecyclerState
import com.cat.daily.local.core.recycler.base.BaseRecyclerAdapter
import com.cat.daily.local.core.recycler.diff.RecyclerStateDiffCallback

class RecyclerAdapter : ListAdapter<RecyclerState, RecyclerView.ViewHolder>(
    AsyncDifferConfig.Builder(RecyclerStateDiffCallback()).build()
) {
    private val asyncListDiffer = AsyncListDiffer(this, RecyclerStateDiffCallback())

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

    override fun getItemViewType(position: Int) = baseAdapter.getItemViewType(getItem(position))
}