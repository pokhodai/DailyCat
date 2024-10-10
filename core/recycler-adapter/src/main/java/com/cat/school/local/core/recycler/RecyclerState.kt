package com.cat.school.local.core.recycler

import android.content.Context
import android.view.View
import com.cat.school.local.core.recycler.holder.HolderItemState

interface RecyclerState : HolderItemState {
    val provideId: String
    override val viewType: Int
    override fun getView(context: Context): View
    fun areContentsTheSame(other: RecyclerState) = this == other
}