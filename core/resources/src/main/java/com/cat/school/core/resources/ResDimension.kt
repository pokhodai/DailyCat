package com.cat.school.core.resources

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout

sealed interface ResDimension {
    val value: Int

    data class Px(override val value: Int) : ResDimension

    data class Dp(val dpValue: Int) : ResDimension {
        override val value: Int = dpValue.dp
    }

    data object WrapContent : ResDimension {
        override val value: Int = ViewGroup.LayoutParams.WRAP_CONTENT
    }

    data object MatchParent : ResDimension {
        override val value: Int = ViewGroup.LayoutParams.MATCH_PARENT
    }

    data object MatchConstraint : ResDimension {
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