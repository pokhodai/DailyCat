package com.cat.daily.local.core.uikit.button

import android.content.Context
import androidx.annotation.ColorRes
import com.cat.daily.local.core.recycler.RecyclerState
import com.cat.daily.local.core.uikit.R
import com.cat.daily.local.core.uikit.base.value.ColorValue
import com.cat.daily.local.core.uikit.base.value.DimensionValue
import com.cat.daily.local.core.uikit.base.value.ImageValue

class ButtonItem {

    data class State(
        val id: String,
        val value: String,
        val type: Type,
        val width: DimensionValue = DimensionValue.WrapContent,
        val paddings: DimensionValue.Rect? = null,
        val background: ColorValue? = null,
        val leadingIcon: ImageValue? = null,
        val trailingIcon: ImageValue? = null,
        val onClick: (() -> Unit)? = null
    ): RecyclerState {
        override val provideId: String = id
        override val viewType: Int = ButtonItem::class.java.hashCode()
        override fun getView(context: Context) = ButtonItemView(context)
    }

    sealed class Type(
        @ColorRes open val textColorRes: Int,
        @ColorRes open val buttonColorRes: Int,
        open val radius: DimensionValue.Dp = DimensionValue.Dp(4),
    ) {
        data class Primary(
            @ColorRes override val textColorRes: Int = R.color.textColor2,
            @ColorRes override val buttonColorRes: Int = R.color.actionColor0,
        ) : Type(
            textColorRes = textColorRes,
            buttonColorRes = buttonColorRes
        )

        data class Secondary(
            @ColorRes override val textColorRes: Int = R.color.textColor0,
            @ColorRes override val buttonColorRes: Int = R.color.actionColor0,
        ) : Type(
            textColorRes = textColorRes,
            buttonColorRes = buttonColorRes
        )

        data class Tertiary(
            @ColorRes override val textColorRes: Int = R.color.textColor0,
            @ColorRes override val buttonColorRes: Int = R.color.transparent,
            override val radius: DimensionValue.Dp = DimensionValue.Dp(0)
        ) : Type(
            textColorRes = textColorRes,
            buttonColorRes = buttonColorRes,
            radius = radius
        )
    }
}