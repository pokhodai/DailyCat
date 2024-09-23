package com.cat.school.local.core.uikit.adapter.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.cat.school.local.core.uikit.ui.edit.TextFieldItem
import com.cat.school.local.core.uikit.ui.edit.TextFieldItemView

class TextFieldViewHolder(
    private val view: TextFieldItemView
) : RecyclerView.ViewHolder(view) {

    fun bind(state: TextFieldItem.State) {
        view.bindState(state)
    }
}