package com.cat.daily.local.core.recycler

import android.content.Context
import android.view.View
import com.cat.daily.local.core.recycler.holder.HolderItemState

interface RecyclerState : HolderItemState {
    val provideId: String
    fun areContentsTheSame(other: RecyclerState) = this == other
    override val viewType: Int
    override fun getView(context: Context): View
}