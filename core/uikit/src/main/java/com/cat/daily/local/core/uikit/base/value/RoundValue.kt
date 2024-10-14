package com.cat.daily.local.core.uikit.base.value

sealed interface RoundValue {
    data class Radius(val value: DimensionValue.Dp) : RoundValue
    data class Corners(
        val leftTopRound: DimensionValue.Dp,
        val rightTopRound: DimensionValue.Dp,
        val leftBottomRound: DimensionValue.Dp,
        val rightBottomRound: DimensionValue.Dp,
    ) : RoundValue
}