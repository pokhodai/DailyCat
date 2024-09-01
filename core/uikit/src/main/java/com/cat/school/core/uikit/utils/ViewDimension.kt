package com.cat.school.core.uikit.utils

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.cat.school.core.uikit.ext.dp

sealed interface ViewDimension {
    val value: Int

    data class Px(override val value: Int) : ViewDimension

    data class Dp(val dpValue: Int) : ViewDimension {
        override val value: Int = dpValue.dp
    }

    data object WrapContent : ViewDimension {
        override val value: Int = ViewGroup.LayoutParams.WRAP_CONTENT
    }

    data object MatchParent : ViewDimension {
        override val value: Int = ViewGroup.LayoutParams.MATCH_PARENT
    }

    data object MatchConstraint : ViewDimension {
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