package com.cat.school.local.core.recycler.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cat.school.local.core.recycler.RecyclerItemView
import com.cat.school.local.core.recycler.RecyclerState

internal class RecyclerViewHolder(
    private val view: View,
    val viewType: Int,
) : RecyclerView.ViewHolder(view) {

    fun bind(
        state: RecyclerState,
    ) {
        (view as? RecyclerItemView<RecyclerState>)?.bindState(state)
    }
}