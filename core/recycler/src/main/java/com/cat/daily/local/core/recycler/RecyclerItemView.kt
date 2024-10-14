package com.cat.daily.local.core.recycler

interface RecyclerItemView<T : RecyclerState> {
    fun bindState(state: T)
}