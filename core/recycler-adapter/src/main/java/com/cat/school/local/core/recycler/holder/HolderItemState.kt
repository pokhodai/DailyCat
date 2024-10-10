package com.cat.school.local.core.recycler.holder

import android.content.Context
import android.view.View

interface HolderItemState {
    fun getView(context: Context): View
    val viewType: Int
}