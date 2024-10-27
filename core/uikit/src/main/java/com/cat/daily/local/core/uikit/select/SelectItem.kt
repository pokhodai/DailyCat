package com.cat.daily.local.core.uikit.select

import android.content.Context
import com.cat.daily.local.core.recycler.RecyclerState
import com.cat.daily.local.core.uikit.base.value.DimensionValue

class SelectItem {

    data class State(
        val id: String,
        val label: String? = null,
        val value: String? = null,
        val hint: String? = null,
        val data: Any? = null,
        val paddings: DimensionValue.Rect = DimensionValue.Rect(0, 0, 0, 0),
        val onClick: ((state: State) -> Unit)? = null,
    ): RecyclerState {
        override val provideId: String = id
        override val viewType: Int = SelectItem::class.java.hashCode()
        override fun getView(context: Context) = SelectItemView(context)
    }
}