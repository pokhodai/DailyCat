package com.cat.school.local.core.uikit.calendar

import android.content.Context
import android.view.View
import com.cat.school.local.core.recycler.RecyclerState
import com.cat.school.local.core.uikit.R
import com.cat.school.local.core.uikit.base.value.ColorValue

class CalendarItem {

    data class State(
        val id: String,
        val type: Type,
        val day: String,
        val month: String,
        val count: String? = null,
        val isSelected: Boolean = false,
    ): RecyclerState {
        override val provideId: String = id
        override val viewType: Int = CalendarItem::class.java.hashCode()
        override fun getView(context: Context) = CalendarItemView(context)
    }

    sealed class Type(
        val colorValue: ColorValue
    ) {
        data object Action : Type(ColorValue.Res(R.color.actionColor0))
        data object Schedule : Type(ColorValue.Res(R.color.backgroundColor2))
    }
}