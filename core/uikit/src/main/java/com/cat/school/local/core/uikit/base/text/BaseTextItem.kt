package com.cat.school.local.core.uikit.base.text

import android.content.Context
import com.cat.school.local.core.recycler.RecyclerState
import com.cat.school.local.core.uikit.base.ext.RECT_0_0_0_0
import com.cat.school.local.core.uikit.base.value.ColorValue
import com.cat.school.local.core.uikit.base.value.DimensionValue
import com.cat.school.local.core.uikit.base.value.SizeValue
import com.cat.school.local.core.uikit.base.value.TextValue

class BaseTextItem {

    data class State(
        val id: String,
        val value: TextValue,
        val sizeValue: SizeValue = SizeValue(),
        val padding: DimensionValue.Rect = RECT_0_0_0_0,
        val margins: DimensionValue.Rect = RECT_0_0_0_0,
        val background: ColorValue? = null,
    ) : RecyclerState {
        override val provideId: String = id
        override val viewType: Int = BaseTextItem::class.java.hashCode()
        override fun getView(context: Context) = BaseTextItemView(context)
    }
}