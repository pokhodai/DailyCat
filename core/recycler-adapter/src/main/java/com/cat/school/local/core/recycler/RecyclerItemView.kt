package com.cat.school.local.core.recycler

interface RecyclerItemView<T : RecyclerState> {
    fun bindState(state: T)
}