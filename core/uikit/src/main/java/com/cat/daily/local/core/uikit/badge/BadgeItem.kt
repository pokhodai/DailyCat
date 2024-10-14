package com.cat.daily.local.core.uikit.badge

import android.content.Context
import com.cat.daily.local.core.recycler.RecyclerState


class BadgeItem {

    data class State(
        val id: String,
        val value: String,
    ): RecyclerState {
        override val provideId: String = id
        override val viewType: Int = com.cat.daily.local.core.uikit.badge.BadgeItem::class.java.hashCode()
        override fun getView(context: Context) =
            com.cat.daily.local.core.uikit.badge.BadgeItemView(context)
    }
}