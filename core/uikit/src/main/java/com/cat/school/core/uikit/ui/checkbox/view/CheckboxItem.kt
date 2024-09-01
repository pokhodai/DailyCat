package com.cat.school.core.uikit.ui.checkbox.view

import androidx.annotation.ColorInt

class CheckboxItem {

    interface View {
        fun bindState(state: State)
    }

    data class State(
        val id: String,
        val isChecked: Boolean = false,
        @ColorInt val checkedBackgroundColorInt: Int,
    )
}