package com.cat.school.local.core.uikit.ui.toolbar

import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

class ToolbarItem {

    interface View {
        fun bindState(state: State)
    }

    data class State(
        val id: String,
        val title: String? = null,
        @ColorInt val backgroundColorInt: Int? = null,
        @DrawableRes val leadingIcon: Int? = null,
        val trailingText: String? = null,
        val onClickTrailing: (() -> Unit)? = null,
        val onClickLeading: (() -> Unit)? = null
    )
}