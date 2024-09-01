package com.cat.school.core.uikit.ui.badge


class BadgeItem {

    interface View {
        fun bindState(state: State)
    }

    data class State(
        val id: String,
        val value: String,
    )
}