package com.cat.daily.local.core.recycler.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cat.daily.local.core.recycler.base.BaseRecyclerAdapter
import com.cat.daily.local.core.recycler.RecyclerState
import com.cat.daily.local.core.recycler.diff.RecyclerStateDiffCallback

class SimpleRecyclerAdapter : ListAdapter<RecyclerState, RecyclerView.ViewHolder>(
    RecyclerStateDiffCallback()
) {

    private val baseAdapter = BaseRecyclerAdapter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return baseAdapter.onCreateViewHolder(
            parent = parent,
            viewType = viewType
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        baseAdapter.onBindViewHolder(
            item = getItem(position),
            holder = holder,
        )
    }

    override fun getItemViewType(position: Int) = baseAdapter.getItemViewType(item = getItem(position))
}