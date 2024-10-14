package com.cat.school.local.core.uikit.badge

import android.content.Context
import com.cat.school.local.core.recycler.RecyclerState


class BadgeItem {

    data class State(
        val id: String,
        val value: String,
    ): RecyclerState {
        override val provideId: String = id
        override val viewType: Int = BadgeItem::class.java.hashCode()
        override fun getView(context: Context) = BadgeItemView(context)
    }
}