package com.cat.daily.local.core.uikit.checkbox

import android.content.Context
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.cat.daily.local.core.recycler.RecyclerState
import com.cat.daily.local.core.uikit.base.ext.RECT_0_0_0_0
import com.cat.daily.local.core.uikit.base.value.ColorValue
import com.cat.daily.local.core.uikit.base.value.DimensionValue
import com.cat.daily.local.core.uikit.base.value.ImageValue

class CheckboxCompositeItem {

    data class State(
        val id: String,
        val name: String,
        val background: ColorValue,
        val trailingIcon: ImageValue? = null,
        val isChecked: Boolean = false,
        val padding: DimensionValue.Rect = RECT_0_0_0_0,
        val note: String? = null,
        val onClickTrailing: (() -> Unit)? = null,
        val onClickCheckbox: (() -> Unit)? = null,
    ): RecyclerState {
        override val provideId: String = id
        override val viewType: Int = CheckboxCompositeItem::class.java.hashCode()
        override fun getView(context: Context) = CheckboxCompositeItemView(context)
    }
}