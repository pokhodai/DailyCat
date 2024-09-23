package com.cat.school.local.feature.event.create.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cat.school.local.core.uikit.ui.edit.TextFieldItem
import com.cat.school.local.core.uikit.utils.ViewDimension

class CreaterEventDecoration : RecyclerView.ItemDecoration() {

    private val leftPadding = ViewDimension.Dp(16)
    private val rightPadding = ViewDimension.Dp(16)
    private val topPadding = ViewDimension.Dp(16)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        when (view) {
            is TextFieldItem.View -> {
                outRect.left = leftPadding.value
                outRect.right = rightPadding.value
                outRect.top = topPadding.value
            }
        }
    }
}