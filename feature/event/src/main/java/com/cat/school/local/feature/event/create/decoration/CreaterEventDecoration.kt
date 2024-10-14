package com.cat.school.local.feature.event.create.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cat.school.local.core.uikit.field.TextFieldItemView
import com.cat.school.local.core.uikit.base.value.DimensionValue

class CreaterEventDecoration : RecyclerView.ItemDecoration() {

    private val leftPadding = DimensionValue.Dp(16)
    private val rightPadding = DimensionValue.Dp(16)
    private val topPadding = DimensionValue.Dp(16)

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