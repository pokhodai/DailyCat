package com.cat.school.local.feature.event.create.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cat.school.local.core.uikit.ui.field.TextFieldItemView
import com.cat.school.local.core.uikit.base.ViewDimension

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
            is TextFieldItemView -> {
                outRect.left = leftPadding.value
                outRect.right = rightPadding.value
                outRect.top = topPadding.value
            }
        }
    }
}