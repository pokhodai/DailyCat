package com.cat.daily.local.core.uikit.base.value

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.cat.daily.local.core.assist.ext.dp

sealed interface DimensionValue {
    val value: Int

    data class Px(override val value: Int) : DimensionValue

    data class Dp(val dpValue: Int) : DimensionValue {
        override val value: Int = dpValue.dp
    }

    data object WrapContent : DimensionValue {
        override val value: Int = ViewGroup.LayoutParams.WRAP_CONTENT
    }

    data object MatchParent : DimensionValue {
        override val value: Int = ViewGroup.LayoutParams.MATCH_PARENT
    }

    data object MatchConstraint : DimensionValue {
        override val value: Int = ConstraintLayout.LayoutParams.MATCH_CONSTRAINT
    }

    data class Rect(
        private val left: Int,
        private val top: Int,
        private val right: Int,
        private val bottom: Int
    ) {
        val valueLeft = left.dp
        val valueRight = right.dp
        val valueBottom = bottom.dp
        val valueTop = top.dp
    }
}