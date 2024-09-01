package com.cat.school.core.uikit.ui.checkbox

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.cat.school.core.uikit.utils.ViewDimension

class CheckboxCompositeItem {

    interface View {
        fun bindState(state: State)
    }

    data class State(
        val id: String,
        val name: String,
        @ColorInt val backgroundColorInt: Int,
        @DrawableRes val trailingIcon: Int? = null,
        val isChecked: Boolean = false,
        val padding: ViewDimension.Rect? = null,
        val note: String? = null,
        val onClickTrailing: (() -> Unit)? = null,
        val onClickCheckbox: (() -> Unit)? = null,
    )
}