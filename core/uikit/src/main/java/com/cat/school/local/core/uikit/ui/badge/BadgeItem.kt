package com.cat.school.local.core.uikit.ui.badge


class BadgeItem {

    interface View {
        fun bindState(state: State)
    }

    data class State(
        val id: String,
        val value: String,
    )
}