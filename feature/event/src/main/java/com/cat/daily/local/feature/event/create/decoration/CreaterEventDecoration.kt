package com.cat.daily.local.feature.event.create.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cat.daily.local.core.uikit.base.value.DimensionValue
import com.cat.daily.local.core.uikit.field.TextFieldItemView

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