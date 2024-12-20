package com.cat.daily.local.core.uikit.checkbox.view

import com.cat.daily.local.core.uikit.base.value.ColorValue

class CheckboxItem {

    interface View {
        fun bindState(state: State)
    }

    data class State(
        val id: String,
        val isChecked: Boolean = false,
        val backgroundColor: ColorValue,
    )
}