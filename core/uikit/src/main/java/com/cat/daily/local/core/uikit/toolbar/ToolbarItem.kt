package com.cat.daily.local.core.uikit.toolbar

import androidx.annotation.ColorInt
import com.cat.daily.local.core.uikit.base.value.ImageValue

class ToolbarItem {

    interface View {
        fun bindState(state: State)
    }

    data class State(
        val id: String,
        val title: String? = null,
        @ColorInt val backgroundColorInt: Int? = null,
        val leading: ImageValue? = null,
        val trailingText: String? = null,
        val onClickTrailing: (() -> Unit)? = null,
        val onClickLeading: (() -> Unit)? = null
    )
}